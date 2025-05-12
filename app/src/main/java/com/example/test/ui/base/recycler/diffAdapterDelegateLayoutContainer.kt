package com.example.test.ui.base.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

inline fun <reified I : T, T : Any, VB : ViewBinding> diffAdapterDelegateLayoutContainer(
    noinline on: (item: T, items: List<T>, position: Int) -> Boolean = { item, _, _ -> item is I },
    noinline same: (oldItem: I, newItem: I) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    noinline contentEquals: (oldItem: I, newItem: I) -> Boolean = { oldItem, newItem -> oldItem.hashCode() == newItem.hashCode() },
    noinline changePayload: (oldItem: I, newItem: I) -> Any? = { _, _ -> null },
    noinline viewBinding: (layoutInflater: LayoutInflater, parent: ViewGroup) -> VB,
    noinline layoutInflater: (parent: ViewGroup) -> LayoutInflater = { parent -> LayoutInflater.from(parent.context) },
    noinline block: AdapterDelegateViewBindingViewHolder<I, VB>.() -> Unit
) = DiffListItemAdapterDelegate<T>(
    DslDiffUtilCallbackDelegate(on, same, contentEquals, changePayload),
    adapterDelegateViewBinding(viewBinding, on, layoutInflater, block)
)

@Suppress("UNCHECKED_CAST", "WRONG_TYPE_PARAMETER_NULLABILITY_FOR_JAVA_OVERRIDE")
class DslDiffUtilCallbackDelegate<I : T, T>(
    private val on: (item: T, items: List<T>, position: Int) -> Boolean,
    private val areSame: (oldItem: I, newItem: I) -> Boolean,
    private val areContentEquals: (oldItem: I, newItem: I) -> Boolean,
    private val changePayload: (oldItem: I, newItem: I) -> Any?
) : DiffUtilCallbackDelegate<T>() {

    override fun isForViewType(data: T): Boolean {
        return on(data, listOf(data), 0)
    }

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return areSame(oldItem as I, newItem as I)
    }

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return areContentEquals(oldItem as I, newItem as I)
    }

    override fun getChangePayload(oldItem: T & Any, newItem: T & Any): Any? {
        return changePayload(oldItem as I, newItem as I)
    }
}