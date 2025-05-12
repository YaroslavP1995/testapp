package com.example.test.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity  <VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    protected abstract val bindingFactory: LayoutInflate<VB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bindingFactory.invoke(layoutInflater)) {
            binding = this
            setContentView(root)
            initUI()
        }
        observeViewModel()
    }

    open fun VB.initUI() {
        //no op
    }

    open fun observeViewModel() {
        //no op
    }

}