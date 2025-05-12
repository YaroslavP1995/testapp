package com.example.test.ui.presentation.menufragments

import com.example.test.databinding.FragmentSettingsBinding
import com.example.test.ui.base.BaseBindingFragment
import com.example.test.ui.base.extension.Inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {


    override val inflate: Inflate<FragmentSettingsBinding>
        get() = FragmentSettingsBinding::inflate


    override fun FragmentSettingsBinding.initUI() {

    }
}