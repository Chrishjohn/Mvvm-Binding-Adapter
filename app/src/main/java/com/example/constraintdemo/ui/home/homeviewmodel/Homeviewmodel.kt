package com.example.constraintdemo.ui.home.homeviewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constraintdemo.R
import com.example.constraintdemo.data.api.Resource
import com.example.constraintdemo.data.repository.AppRepository
import com.example.constraintdemo.ui.home.model.UserResponse
import com.example.constraintdemo.utils.AppUtils
import kotlinx.coroutines.launch
import retrofit2.Response

class Homeviewmodel (
    var application: Application,
    private var appRepository: AppRepository
): ViewModel(){
    private val _memeResponse = MutableLiveData<Resource<UserResponse>>()
    val memeListResponse: LiveData<Resource<UserResponse>> = _memeResponse

    fun getMemesList() = viewModelScope.launch {
        getMemsData()
    }

    //get  call
    private suspend fun getMemsData() {
        _memeResponse.postValue(Resource.Loading())
        if (AppUtils.hasInternetConnection(application)) {
            val response = appRepository.getMemesRepository()
            _memeResponse.postValue(handleResponse(response))
        } else {
            _memeResponse.postValue(Resource.Error(application.getString(R.string.no_internet_connection)))
        }
    }

    // handle response
    private fun handleResponse(response: Response<UserResponse>): Resource<UserResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        } else if (response.errorBody() != null) {
            AppUtils.getErrorMessage(response.errorBody()!!)?.let { it1 ->
                return Resource.Error(it1)
            }
        }
        return Resource.Error(response.message())
    }
}