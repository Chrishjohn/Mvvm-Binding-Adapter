package com.example.constraintdemo.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.constraintdemo.R
import com.example.karanpractical.ui.dialog.CustomProgressDialog
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private var customProgressDialog: CustomProgressDialog? = null

    fun showSnackBarRed(context: Context?, message: String?) {
        try {
            val toast = Toast.makeText(context,message, Toast.LENGTH_SHORT)

            //val view: View = toast.view!!
            //view.setBackgroundColor(Color.parseColor("#219760"))
            val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater.inflate(R.layout.item_toast_red, null);
            toast.view = view
            //view.setBackgroundResource(R.drawable.toast_background)
            val textView: TextView = view.findViewById(R.id.tvToast)
            textView.text = message
//        textView.setTextColor(Color.parseColor("#ffffff"))
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

/*
    fun logout() {
        val mySharedPreferences = MySharedPreferences.getMySharedPreferences()
        mySharedPreferences!!.isLogin = false
        startLoginActivity()
        finish()
      }
*/

    /**
     * SIMPLE SNACKBAR
     */
    fun showSnackBar(context: Context?, message: String?) {
        try {
            val toast = Toast.makeText(context,message, Toast.LENGTH_SHORT)

            //val view: View = toast.view!!
            //view.setBackgroundColor(Color.parseColor("#219760"))
            val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater.inflate(R.layout.item_toast, null);
            toast.view = view
            //view.setBackgroundResource(R.drawable.toast_background)
            val textView: TextView = view.findViewById(R.id.tvToast)
            textView.text = message
//        textView.setTextColor(Color.parseColor("#ffffff"))
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showProgressDialog(ctx: Context) {
        try {
            customProgressDialog = CustomProgressDialog(ctx)
            customProgressDialog!!.show()
            customProgressDialog!!.setCancelable(false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideProgressDialog() {
        try {
            if (customProgressDialog != null && customProgressDialog!!.isShowing) {
                customProgressDialog!!.dismiss()
                customProgressDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    val activity: Activity
        get() = this
}