package com.example.test.ui.base.extension

import android.util.Log
import com.example.test.ui.base.exceptions.AppException
import com.example.test.ui.base.exceptions.Errors
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

suspend fun <T> safeResultCall(call: suspend () -> T): Result<T> {
    return try {
        val callResult = call.invoke()
        Result.success(callResult)
    } catch (e: Throwable) {
        val defaultErrorMessage = "Oops...something wrong"
        val appException = when (e) {
            is ConnectException,
            is UnknownHostException
                -> AppException.NetworkException(cause = e)

            is HttpException -> {
                when (e.code()) {
                    401 -> {
                        AppException.HttpException.ClientException("Unauthorized", e.code(), e)
                    }

                    in 400..5002 -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        val errorsData = if (errorBody != null) {
                            try {
                                val errorResponse =
                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                                errorResponse
                            } catch (ex: JsonParseException) {
                                defaultErrorMessage
                            }
                        } else {
                            Errors()
                        }


                        val errorMessage = if (errorBody != null) {
                            try {
                                val errorResponse =
                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                                errorResponse.error

                            } catch (ex: JsonParseException) {
                                val errorResponse =
                                    Gson().fromJson(errorBody, ErrorResponseBody::class.java)
                                errorResponse.error

                            } catch (ex: Exception) {
                                defaultErrorMessage
                            }
                        } else {
                            defaultErrorMessage
                        }

                        try {
                            if (errorsData != null && errorsData as ErrorResponse? != null) {
                                AppException.HttpException.FieldValidationException(
                                    errorsData.errors,
                                    errorMessage,
                                    e,
                                    error = errorsData.error
                                )
                            } else {
                                AppException.HttpException.ClientException(
                                    errorMessage,
                                    e.code(),
                                    e
                                )
                            }
                        } catch (ex: Exception) {
                            AppException.HttpException.ClientException(errorMessage, e.code(), e)

                        }

                    }

                    in 500..599 -> AppException.HttpException.ServerException(
                        errorCode = e.code(),
                        cause = e
                    )

                    else -> AppException.HttpException(errorCode = e.code(), cause = e)
                }
            }

            is JsonSyntaxException -> AppException.ParseJsonException(cause = e)
            else -> AppException.SomethingBadHappenedException(
                message = defaultErrorMessage,
                cause = e
            )
        }
        Result.failure(appException)
    }
}

data class ErrorResponseBody(@SerializedName("errors") var error: String? = null)
data class ErrorResponse(
    val message: String,
    @SerializedName("error_code") var error_code: String,
    @SerializedName("error_description") var error_description: String,
    @SerializedName("errors") var errors: Errors?,
    @SerializedName("context") val context: String,
    @SerializedName("status_code") val status_code: String,
    @SerializedName("error") val error: String
)
open class BaseResponse(
    @SerializedName("error_code") val error_code: String? = null,
    @SerializedName("error_description") val error_description: String? = null
)