package com.example.constraintdemo.data.api

import com.example.constraintdemo.data.api.RestConstant
import com.example.constraintdemo.ui.home.model.UserResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET(RestConstant.SellerList)
    suspend fun getSellerlist(): Response<UserResponse>

}