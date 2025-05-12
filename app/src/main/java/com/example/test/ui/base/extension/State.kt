package com.example.test.ui.base.extension

sealed class State
object IdleState : State()
open class CompletedState<out T : Any>(val data: T) : State()
data class ErrorState(val message: String?, val exception: Throwable? = null) : State()
