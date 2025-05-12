package com.example.test.ui.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.example.test.ui.base.extension.ErrorState
import com.example.test.ui.base.extension.IdleState
import com.example.test.ui.base.extension.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.SocketTimeoutException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val parentJob = Job()
    protected val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Default

    private val _connectionErrorState = MutableStateFlow<State>(IdleState)
    val connectionErrorState: StateFlow<State>
        get() = _connectionErrorState

    @CallSuper
    protected open fun handleException(throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> {
                _connectionErrorState.value = ErrorState(throwable.localizedMessage, throwable)
            }
            else -> {
                Log.e(javaClass.simpleName, throwable.localizedMessage, throwable)
            }
        }
    }

    data class PopupConfig(
        val title: String? = null,
        val message: String? = null,
        val positiveText: String? = null,
        val negativeText: String? = null,
    )
}