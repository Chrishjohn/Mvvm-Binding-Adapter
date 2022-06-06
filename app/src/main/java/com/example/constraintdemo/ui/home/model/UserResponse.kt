package com.example.constraintdemo.ui.home.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class MemesItem(

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	var url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("box_count")
	val boxCount: Int? = null
)

data class Data(

	@field:SerializedName("memes")
	val memes: List<MemesItem?>? = null
)
