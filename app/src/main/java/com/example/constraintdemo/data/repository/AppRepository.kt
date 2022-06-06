package com.example.constraintdemo.data.repository

import com.example.constraintdemo.data.api.RetrofitClient


class AppRepository {
    suspend fun getMemesRepository() =
        RetrofitClient.apiInterface.getSellerlist()

}
