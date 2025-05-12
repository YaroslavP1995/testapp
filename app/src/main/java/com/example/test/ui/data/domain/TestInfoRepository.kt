package com.example.test.ui.data.domain

import com.example.test.ui.data.dto.body.LoginBody
import com.example.test.ui.data.dto.response.DevicesDataResponse
import com.example.test.ui.data.dto.response.MappedData
import com.example.test.ui.data.dto.response.TokenResponse

interface TestInfoRepository {
    suspend fun login(code: LoginBody): Result<TokenResponse>
    suspend fun getDeviceList(): Result<DevicesDataResponse>
    fun mapCameras(data: DevicesDataResponse):List<MappedData>
}