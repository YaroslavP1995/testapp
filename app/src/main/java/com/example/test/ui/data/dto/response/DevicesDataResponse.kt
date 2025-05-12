package com.example.test.ui.data.dto.response

import com.google.gson.annotations.SerializedName

data class DevicesDataResponse(
    @SerializedName("error_code") var errorCode: Int? = null,
    @SerializedName("error_description") var errorDescription: String? = null,
    @SerializedName("error") var error: String? = null,
    @SerializedName("cameras_group_id") var camerasGroupId: Int? = null,
    @SerializedName("cameras_group_name") var camerasGroupName: String? = null,
    @SerializedName("cameras_group_root_id") var camerasGroupRootId: Int? = null,
    @SerializedName("cameras_group_full_path") var camerasGroupFullPath: String? = null,
    @SerializedName("cameras_groups") var camerasGroups: ArrayList<String> = arrayListOf(),
    @SerializedName("user_devices") var userDevices: ArrayList<UserDevices> = arrayListOf(),
    @SerializedName("view_presets") var viewPresets: ArrayList<ViewPresets> = arrayListOf(),
    @SerializedName("shared_access") var sharedAccess: ArrayList<String> = arrayListOf()
)

data class UserDevices(
    @SerializedName("device_hwid") var deviceHwid: Long? = null,
    @SerializedName("device_name") var deviceName: String? = null,
    @SerializedName("device_login") var deviceLogin: String? = null,
    @SerializedName("device_password") var devicePassword: String? = null,
    @SerializedName("device_udid") var deviceUdid: Int? = null,
    @SerializedName("device_timezone") var deviceTimezone: Int? = null,
    @SerializedName("device_tzname") var deviceTzname: String? = null,
    @SerializedName("is_online") var isOnline: Boolean? = null,
    @SerializedName("is_credentials_valid") var isCredentialsValid: Boolean? = null,
    @SerializedName("stream") var stream: String? = null,
    @SerializedName("device_type") var deviceType: Int? = null,
    @SerializedName("device_channels") var deviceChannels: Int? = null,
    @SerializedName("device_cameras") var deviceCameras: ArrayList<DeviceCameras> = arrayListOf()
)

data class DeviceCameras(
    @SerializedName("camera_id") var cameraId: Int? = null,
    @SerializedName("camera_channel") var cameraChannel: Int? = null,
    @SerializedName("camera_name") var cameraName: String? = null,
    @SerializedName("camera_is_active") var cameraIsActive: Boolean? = null,
    @SerializedName("camera_is_armed") var cameraIsArmed: Boolean? = null,
    @SerializedName("camera_uses_hls_storage") var cameraUsesHlsStorage: Boolean? = null,
    @SerializedName("camera_uses_main_streaming") var cameraUsesMainStreaming: Boolean? = null,
    @SerializedName("camera_has_cloud_recording") var cameraHasCloudRecording: Boolean? = null,
    @SerializedName("camera_has_cloud_streaming") var cameraHasCloudStreaming: Boolean? = null,
    @SerializedName("camera_has_cloud_snapshots") var cameraHasCloudSnapshots: Boolean? = null,
    @SerializedName("device_hwid") var deviceHwid: Long? = null,
    @SerializedName("camera_settings") var cameraSettings: CameraSettings?
)

data class CameraSettings(
    @SerializedName("ptz") var ptz: Ptz?
)

data class ViewPresetScheme(
    @SerializedName("x") var x: Int? = null,
    @SerializedName("y") var y: Int? = null,
    @SerializedName("rows") var rows: Int? = null,
    @SerializedName("cols") var cols: Int? = null
)

data class Ptz(
    @SerializedName("swap_horizontal") var swapHorizontal: Boolean? = null,
    @SerializedName("swap_vertical") var swapVertical: Boolean? = null
)

data class ViewPresets(
    @SerializedName("view_preset_id") var viewPresetId: Int? = null,
    @SerializedName("view_preset_name") var viewPresetName: String? = null,
    @SerializedName("view_preset_scheme") var viewPresetScheme: ArrayList<ViewPresetScheme> = arrayListOf(),
    @SerializedName("view_preset_devices") var viewPresetDevices: ArrayList<ViewPresetDevices> = arrayListOf()
)


data class ViewPresetDevices(
    @SerializedName("camera_position") var cameraPosition: Int? = null,
    @SerializedName("device") var device: Device? = Device()
)

data class Device(
    @SerializedName("device_hwid") var deviceHwid: Long? = null,
    @SerializedName("device_name") var deviceName: String? = null,
    @SerializedName("device_login") var deviceLogin: String? = null,
    @SerializedName("device_password") var devicePassword: String? = null,
    @SerializedName("device_udid") var deviceUdid: Long? = null,
    @SerializedName("device_timezone") var deviceTimezone: Int? = null,
    @SerializedName("device_tzname") var deviceTzname: String? = null,
    @SerializedName("is_online") var isOnline: Boolean? = null,
    @SerializedName("is_credentials_valid") var isCredentialsValid: Boolean? = null,
    @SerializedName("stream") var stream: String? = null,
    @SerializedName("device_type") var deviceType: Int? = null,
    @SerializedName("device_channels") var deviceChannels: Int? = null,
    @SerializedName("device_cameras") var deviceCameras: ArrayList<DeviceCameras> = arrayListOf()

)