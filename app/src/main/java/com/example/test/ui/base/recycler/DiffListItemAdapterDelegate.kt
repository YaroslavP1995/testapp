package com.example.test.ui.base.recycler

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

data class DiffListItemAdapterDelegate<I>(
    val diffItemCallback: DiffUtilCallbackDelegate<I>,
    val delegate: AdapterDelegate<List<I>>
)
