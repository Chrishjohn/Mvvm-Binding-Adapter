package com.example.constraintdemo.ui.authentication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.constraintdemo.R
import com.example.constraintdemo.databinding.ActivityMainBinding
import com.example.constraintdemo.ui.authentication.factory.UserViewmodelFactory
import com.example.constraintdemo.ui.authentication.viewmodel.AuthViewmodel
import com.example.constraintdemo.utils.AppUtils
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    private lateinit var binding: ActivityMainBinding
    override val kodein: Kodein by kodein()
    private lateinit var loginViewModel: AuthViewmodel
    private val factory: UserViewmodelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        setupViewModel()
        setupObservers()
    }
    private fun setupViewModel() {
        ViewModelProvider(this, factory).get(AuthViewmodel::class.java)
            .also { loginViewModel = it }
        loginViewModel.also { binding.viewModel = it }
    }
    private fun setupObservers() {
        loginViewModel.errorString.observe(this, {
            AppUtils.showShortToast(this, it.toString())
        })
    }

}