package com.example.test.ui.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test.ui.base.BaseViewModel
import com.example.test.ui.base.exceptions.AppException
import com.example.test.ui.data.domain.TestInfoRepository
import com.example.test.ui.data.dto.body.LoginBody
import com.example.test.ui.data.dto.response.MappedData
import com.example.test.ui.data.dto.response.TokenResponse
import com.example.test.ui.data.header.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: TestInfoRepository,
    private val tokenRepository: TokenRepository,
) : BaseViewModel() {

    private val _tokensInfo = MutableLiveData<TokenResponse>()
    val tokensInfo: LiveData<TokenResponse> get() = _tokensInfo

    private val _devicesDataResponse = MutableLiveData<List<MappedData>>()
    val devicesDataResponse: LiveData<List<MappedData>> get() = _devicesDataResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun isTokenExists(): Boolean {
        return tokenRepository.isTokenExists()
    }

    fun login(body: LoginBody) {
        viewModelScope.launch {
            authRepository.login(body)
                .onSuccess {
                    if (it.error_description.equals("Success")) {
                        tokenRepository.saveToken(it.short_token)
                        _tokensInfo.value = it
                    } else {
                        _errorMessage.value = it.error_description
                    }
                }
                .onFailure {
                    if (it is AppException) {
                        Log.d("yarek", "Custom error1: ${it.message}")
                    } else {
                        Log.d("yarek", "System error1: ${it.localizedMessage}")
                    }
                }
        }
    }

    fun getDeviceList() {
        viewModelScope.launch {
            authRepository.getDeviceList()
                .onSuccess {
                    if (!it.error.isNullOrEmpty()) {
                        _errorMessage.value = it.error.toString()
                    } else {
                        _devicesDataResponse.value = authRepository.mapCameras(it)
                    }
                }
                .onFailure {
                    if (it is AppException) {
                        Log.d("yarek", "Custom error: ${it.message}")
                    } else {
                        Log.d("yarek", "System error: ${it.localizedMessage}")
                    }
                }
        }
    }

}