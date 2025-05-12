package com.example.test.ui.presentation.recyclerviewAD

import com.example.test.databinding.ItemDevicesBinding
import com.example.test.ui.base.recycler.diffAdapterDelegateLayoutContainer
import com.example.test.ui.data.dto.response.MappedData

fun devicesAdapterDelegate(
    selectDeviceClick: (MappedData) -> Unit,
) = diffAdapterDelegateLayoutContainer<MappedData, Any, ItemDevicesBinding>(
    viewBinding = { inflater, root -> ItemDevicesBinding.inflate(inflater, root, false) },
    on = { item: Any, _: List<Any>, _: Int -> item is MappedData },
    same = { oldItem: MappedData, newItem: MappedData -> oldItem == newItem },
    contentEquals = { oldItem: MappedData, newItem: MappedData ->
        oldItem == newItem
    }
) {
    bind {
        with(binding) {
            name.text = item.name
            icon.setImageResource(item.iconResId)
            root.setOnClickListener {
                selectDeviceClick.invoke(item)
            }
        }
    }
}