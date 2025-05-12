package com.example.test.ui.base.exceptions

import com.example.test.R
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException

sealed class AppException(
    override val message: String?,
    cause: Throwable? = null,
    var errors: Errors? = null,
    val messageResource: StringResource = StringResource(R.string.exceptions_something_wrong),
    var error: String? = null,

    ) : Exception(message, cause) {


    class NetworkException(
        message: String? = null,
        cause: Throwable? = null,
        messageResource: StringResource = StringResource(R.string.exceptions_no_internet)
    ) : AppException(message, cause, null, messageResource)

    open class HttpException(
        message: String? = null,
        val errorCode: Int,
        errorst: Errors? = null,
        cause: Throwable? = null,
        messageResource: StringResource = StringResource(R.string.exceptions_http_error),
        error: String? = null,
    ) : AppException(message, cause, errorst, messageResource, error) {

        class ClientException(
            message: String? = null,
            errorCode: Int,
            cause: Throwable? = null,
            messageResource: StringResource = StringResource(R.string.exceptions_client)
        ) : HttpException(message, errorCode, null, cause, messageResource)

        class ServerException(
            message: String? = null,
            errorCode: Int,
            cause: Throwable? = null,
            messageResource: StringResource = StringResource(R.string.exceptions_server)
        ) : HttpException(message, errorCode, null, cause, messageResource)

        class FieldValidationException(
            errors: Errors? = null,
            message: String? = null,
            cause: Throwable? = null,
            messageResource: StringResource = StringResource(R.string.exceptions_field_validation),
            error: String? = null,
        ) : HttpException(message, 0, errors, cause, messageResource, error)
    }

    class ParseJsonException(
        message: String? = null,
        cause: Throwable? = null,
        messageResource: StringResource = StringResource(R.string.exceptions_parse_json)
    ) : AppException(message, cause, null, messageResource)

    class SomethingBadHappenedException(
        message: String? = null,
        cause: Throwable? = null,
        messageResource: StringResource = StringResource(R.string.exceptions_something_wrong)
    ) : AppException(message, cause, null, messageResource)
}

class ErrorNT(
    @SerializedName("context") val context: String? = null,
    @SerializedName("errors") val message: String? = null,
    @SerializedName("error") val errors: Map<String, List<String>>
)

data class Errors(
    @SerializedName("otp") var otp: ArrayList<String> = arrayListOf(),
    @SerializedName("password") var password: ArrayList<String> = arrayListOf(),
)
