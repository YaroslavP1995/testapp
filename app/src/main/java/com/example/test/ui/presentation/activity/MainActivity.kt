package com.example.test.ui.presentation.activity

import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.test.R
import com.example.test.databinding.MainActivityBinding
import com.example.test.ui.base.BaseActivity
import com.example.test.ui.base.LayoutInflate
import com.example.test.ui.base.extension.navigate
import com.example.test.ui.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    override val bindingFactory: LayoutInflate<MainActivityBinding>
        get() = MainActivityBinding::inflate
    private val viewModel: MainViewModel by viewModels()
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun MainActivityBinding.initUI() {
        setSupportActionBar(toolbar)
        navController.setGraph(R.navigation.nav_graph)

        drawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )


        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navView.setupWithNavController(navController)
    }

}