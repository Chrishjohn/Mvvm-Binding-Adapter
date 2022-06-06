package com.example.constraintdemo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.constraintdemo.ui.home.activity.HomeActivity

fun Context.startHomeActivity() =
    Intent(this, HomeActivity::class.java).also {
        startActivity(it)
        (this as Activity).finish()
    }


