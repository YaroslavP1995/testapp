package com.example.test.ui.data.api

import com.example.test.ui.data.dto.response.DevicesDataResponse
import com.example.test.ui.data.dto.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PartizanApi {

    @FormUrlEncoded
    @POST("security/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): TokenResponse

    @GET("rest/DeviceList/getDeviceList")
    suspend fun getDeviceList(
    ): DevicesDataResponse

}