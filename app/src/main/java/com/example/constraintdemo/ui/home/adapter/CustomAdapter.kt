package com.example.constraintdemo.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.constraintdemo.R
import com.example.constraintdemo.databinding.ItemListBinding
import com.example.constraintdemo.ui.home.activity.HomeActivity
import com.example.constraintdemo.ui.home.model.Data
import com.example.constraintdemo.ui.home.model.MemesItem
import com.example.constraintdemo.ui.home.model.UserResponse

class CustomAdapter(items: List<MemesItem?>,var context: HomeActivity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private lateinit var binding: ItemListBinding

    // refresh items
    var items = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
/*
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list,
                parent,
                false
            )
        )
*/
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val largeNews = items[position]
        holder.bind(largeNews)

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(largeNews: MemesItem?) {
            binding.model = largeNews
            binding.imageView2.setOnClickListener {
                Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
            }
        }
     }
}