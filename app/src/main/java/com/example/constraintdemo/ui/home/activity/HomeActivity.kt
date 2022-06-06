package com.example.constraintdemo.ui.home.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constraintdemo.R
import com.example.constraintdemo.data.api.Resource
import com.example.constraintdemo.databinding.ActivityHomeBinding
import com.example.constraintdemo.ui.home.factory.HomeviewmodelFactory
import com.example.constraintdemo.ui.home.homeviewmodel.Homeviewmodel
import com.example.constraintdemo.base.BaseActivity
import com.example.constraintdemo.ui.home.adapter.CustomAdapter
import com.example.constraintdemo.ui.home.model.MemesItem
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity(), KodeinAware {
    private lateinit var binding: ActivityHomeBinding
    override val kodein: Kodein by kodein()
    private lateinit var homeviewmodel: Homeviewmodel
    private val factory: HomeviewmodelFactory by instance()
    var linerLayoutManager: LinearLayoutManager? = null
    var memesAdapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
        setupViewModel()
        setupObservers()
        homeviewmodel.getMemesList()

    }
    private fun setupViewModel() {
        ViewModelProvider(this, factory).get(Homeviewmodel::class.java)
            .also { homeviewmodel = it }
        homeviewmodel.also { binding.model = it }
    }

    //set observer for currency
    private fun setupObservers() {
        homeviewmodel.memeListResponse.observe(
            this,
            { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressDialog()
                        response.data?.let { memeListResponse ->
                            Toast.makeText(activity, "success", Toast.LENGTH_LONG).show()
                            retrieveList(memeListResponse.data?.memes)
                        }
                    }
                    is Resource.Error -> {
                        hideProgressDialog()
                        response.message?.let { message ->
                            showSnackBar(activity, message)
                        }
                    }
                    is Resource.Loading -> {
                        activity?.let { showProgressDialog(it) }
                    }
                }
            })

    }
    private fun retrieveList(data: List<MemesItem?>?) {
        linerLayoutManager = LinearLayoutManager(
            activity
        )
        binding.recyclerView.layoutManager = linerLayoutManager
        memesAdapter = data?.let { CustomAdapter(it,this) }
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = memesAdapter

    }
}