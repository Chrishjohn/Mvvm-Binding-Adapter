package com.example.constraintdemo.ui.authentication.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.constraintdemo.R
import com.example.constraintdemo.data.repository.AppRepository
import com.example.constraintdemo.utils.startHomeActivity

class AuthViewmodel (
    var application: Application,
    private var appRepository: AppRepository
): ViewModel(){
    var errorString = MutableLiveData<String>()
    var memail: String? = ""
    var pwd: String = ""
    private fun checkLoginValidation(): String {
        return when {
            TextUtils.isEmpty(memail) -> {
                application.getString(R.string.please_enter_user_email)
            }
            TextUtils.isEmpty(pwd) -> {
                application.getString(R.string.please_enter_pwd)
            }
            else -> {
                ""
            }
        }
    }
    fun apiCall(view: View) {
        if (TextUtils.isEmpty(checkLoginValidation())) {
            view.context.startHomeActivity()

        } else {
            errorString.value = checkLoginValidation()
        }
    }
}
