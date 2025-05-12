package com.example.test.ui.base.recycler

import androidx.recyclerview.widget.DiffUtil

abstract class DiffUtilCallbackDelegate <T> : DiffUtil.ItemCallback<T>() {
    abstract fun isForViewType(data: T): Boolean
}