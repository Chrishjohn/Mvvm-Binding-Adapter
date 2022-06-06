package com.example.constraintdemo.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.regex.Pattern
import kotlin.Int as Int1


object AppUtils {
    private const val APP_TAG = "HobNob"

    fun logString(message: String?): Int1 {
        return Log.i(APP_TAG, message!!)
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    @JvmStatic
    fun isValidPassword(password: String?): Boolean {
        val PASSWORDPATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$"
        val pattern = Pattern.compile(PASSWORDPATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

   /* fun isPhoneValid(phone: String?): Boolean {
        val expression = "^+(?!0+\$)(?:[0-9]?){6,16}[0-9]\$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }*/

    fun isPhoneValid(phone: String?): Boolean {
        val expression = "^+(?:[0-9] ?){6,16}[0-9]\$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    @JvmStatic
    fun getText(textView: TextView): String {
        return textView.text.toString().trim { it <= ' ' }
    }

    fun showLongToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showShortToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getErrorMessage(responseBody: ResponseBody): String? {
        var string = ""
        try {
            val jsonObject = JSONObject(responseBody.string())
            if (jsonObject.getJSONObject("Data") != null) {

            } else {
                jsonObject.getString("message")
            }
        } catch (e: Exception) {
            string = e.message.toString()
        }
        return string
    }


    @JvmStatic
    fun hideSoftKeyboard(activity: Activity) {
        val focusedView = activity.currentFocus
        if (focusedView != null) {
            val inputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
    }

    // Check EditText or String is Empty or null etc.
    fun isEmpty(str: String?): Boolean {
        return TextUtils.isEmpty(str)
    }

    fun getRequestBody(value: String?): RequestBody? {
        return value!!.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

    fun hasInternetConnection(application: Application): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}