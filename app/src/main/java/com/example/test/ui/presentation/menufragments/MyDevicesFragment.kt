package com.example.test.ui.presentation.menufragments

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.test.R
import com.example.test.databinding.FragmentMyDevicesBinding
import com.example.test.ui.base.BaseBindingFragment
import com.example.test.ui.base.extension.Inflate
import com.example.test.ui.base.extension.toast
import com.example.test.ui.base.recycler.AsyncListDiffDelegationAdapter
import com.example.test.ui.presentation.recyclerviewAD.devicesAdapterDelegate
import com.example.test.ui.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDevicesFragment : BaseBindingFragment<FragmentMyDevicesBinding>() {

    override val inflate: Inflate<FragmentMyDevicesBinding>
        get() = FragmentMyDevicesBinding::inflate

    private val viewModel: MainViewModel by viewModels(ownerProducer = { requireActivity() })
    private val devicesAdapter = AsyncListDiffDelegationAdapter(
        devicesAdapterDelegate(
        ) {
            requireActivity().toast(getString(R.string.device, it.name))
        }
    )

    override fun FragmentMyDevicesBinding.initUI() {
        viewModel.getDeviceList()
        initObserbers()
    }

    private fun initObserbers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                requireActivity().onBackPressed()
            }
        }
        viewModel.devicesDataResponse.observe(viewLifecycleOwner) {
            binding.recycler.adapter = devicesAdapter
            devicesAdapter.items = it
        }
    }
}