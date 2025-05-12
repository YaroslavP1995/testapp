package com.example.test.ui.presentation.menufragments

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentLoginBinding
import com.example.test.ui.base.BaseBindingFragment
import com.example.test.ui.base.extension.Inflate
import com.example.test.ui.base.extension.toast
import com.example.test.ui.data.dto.body.LoginBody
import com.example.test.ui.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {

    override val inflate: Inflate<FragmentLoginBinding>
        get() = FragmentLoginBinding::inflate

    private val viewModel: MainViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentLoginBinding.initUI() {
        restoreInputState()
        initTextListeners()
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {
        with(viewModel) {
            tokensInfo.observe(viewLifecycleOwner) { tokenInfo ->
                if (tokenInfo.short_token.isNotEmpty()){
                    findNavController().navigate(R.id.deviceFragment)
                }
            }
            errorMessage.observe(viewLifecycleOwner) { errorMessage ->
                if (errorMessage.isNotEmpty()) {
                    requireActivity().toast(errorMessage)
                }
            }
        }
    }

    private fun initClickListeners() {
        with(binding) {
            signInButton.setOnClickListener {
                login(
                    email = emailEditText.text.toString(),
                    password = passworEditText.text.toString()
                )
            }

            registrationButton.setOnClickListener {
                requireActivity().toast("Не реалізовано")
            }
        }
    }

    private fun login(email: String?, password: String?) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            requireActivity().toast("Усі поля маютб бути заповнені")
        } else {
            viewModel.login(LoginBody(email, password))
        }
    }

    private fun initTextListeners() {
        with(binding) {
            emailEditText.doAfterTextChanged {
                viewModel.setEmail(it.toString())
            }

            passworEditText.doAfterTextChanged {
                viewModel.setPassword(it.toString())
            }
        }
    }

    private fun FragmentLoginBinding.restoreInputState() {
        viewModel.email.observe(viewLifecycleOwner) { email ->
            if (emailEditText.text.toString() != email) {
                emailEditText.setText(email)
            }
        }

        viewModel.password.observe(viewLifecycleOwner) { password ->
            if (passworEditText.text.toString() != password) {
                passworEditText.setText(password)
            }
        }
    }
}