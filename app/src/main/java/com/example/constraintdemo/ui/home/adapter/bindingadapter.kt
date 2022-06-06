package com.example.constraintdemo.ui.home.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage( url: String?) {
    if (!url.isNullOrEmpty()) {
       Glide.with(this.context).load(url).into(this)
    }

}