package com.example.test.ui.base.exceptions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.test.R

sealed interface DataState <T> {

    object Loading : DataState<Any>

    data class Data<T>(val value: T) : DataState<T>

    data class Error<T>(
        @DrawableRes val iconRes: Int = R.drawable.ic_launcher_background,
        val stringResource: StringResource? = null,
        val message: String? = null,
    ) : DataState<T>

    val isErrorState get() = this is Error<T>

    val valueOrNull get() = (this as? Data)?.value


}
data class StringResource(@StringRes val resId: Int, val args: Array<Any> = emptyArray()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StringResource

        if (resId != other.resId) return false
        if (!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = resId
        result = 31 * result + args.contentHashCode()
        return result
    }
}
