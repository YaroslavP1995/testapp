package com.example.test.ui.data.repo

import com.example.test.R
import com.example.test.ui.base.extension.safeResultCall
import com.example.test.ui.data.api.PartizanApi
import com.example.test.ui.data.domain.TestInfoRepository
import com.example.test.ui.data.dto.body.LoginBody
import com.example.test.ui.data.dto.response.DevicesDataResponse
import com.example.test.ui.data.dto.response.MappedData
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val api: PartizanApi,
) : TestInfoRepository {

    override suspend fun login(code: LoginBody) =
        safeResultCall {
            api.login(code.email, code.password)
        }

    override suspend fun getDeviceList() =
        safeResultCall {
            api.getDeviceList()
        }

    override fun mapCameras(jsonResponse: DevicesDataResponse): List<MappedData> {
        val result = mutableListOf<MappedData>()

        jsonResponse.userDevices.forEach { device ->
            result.add(
                MappedData(
                    name = device.deviceName ?: "Unnamed",
                    iconResId = R.drawable.group_403
                )
            )
        }

        jsonResponse.viewPresets.forEach { preset ->
            result.add(
                MappedData(
                    name = preset.viewPresetName ?: "Unnamed",
                    iconResId = R.drawable.group_405
                )
            )
        }
        return result
    }
}