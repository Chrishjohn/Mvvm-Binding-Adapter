package com.example.constraintdemo.ui.home.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.constraintdemo.data.repository.AppRepository
import com.example.constraintdemo.ui.home.homeviewmodel.Homeviewmodel

class HomeviewmodelFactory (
    private val application: Application,
    val appRepository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Homeviewmodel(application, appRepository) as T
    }
}