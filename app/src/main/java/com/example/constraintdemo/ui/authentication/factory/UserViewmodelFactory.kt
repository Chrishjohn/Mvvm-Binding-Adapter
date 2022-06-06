package com.example.constraintdemo.ui.authentication.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.constraintdemo.data.repository.AppRepository
import com.example.constraintdemo.ui.authentication.viewmodel.AuthViewmodel

@Suppress("UNCHECKED_CAST")
class UserViewmodelFactory(
    private val application: Application,
    val appRepository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewmodel(application, appRepository) as T
    }
}