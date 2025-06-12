package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.NetworkCallFiles.ApiRepository
import com.example.learningproject.NetworkCallFiles.ApiState
import com.example.learningproject.NetworkCallFiles.LoginError
import com.example.learningproject.NetworkCallFiles.RetrofitInstance
import com.example.learningproject.NetworkCallFiles.SingleUserResponseForGson
import com.example.learningproject.NetworkCallFiles.UserLogin
import com.example.learningproject.NetworkCallFiles.UserLoginResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RetrofitPracticeVM: ViewModel() {
    companion object {
        val apiRepo = ApiRepository()
    }
    val service = RetrofitInstance.apiInterface

    private val mGetSingleUserState = MutableLiveData<ApiState>(ApiState.Idle)
    val getSingleUserState: LiveData<ApiState>
        get() = mGetSingleUserState

    private val mGetSingleUserResponse = MutableLiveData<SingleUserResponseForGson>()
    val getSingleUserResponse: LiveData<SingleUserResponseForGson>
        get() = mGetSingleUserResponse

    // defining the RetrofitApiState for Login Api
    private val mLoginState = MutableLiveData<RetrofitApiStateNormal>(RetrofitApiStateNormal.Idle())
    val loginState: LiveData<RetrofitApiStateNormal>
        get() = mLoginState

    fun getSingleUser() {
        viewModelScope.launch {
            try {
                val response = service.getSingleUser(2)
                if (response.isSuccessful) {
                    mGetSingleUserResponse.value = response.body()
                    Log.i("TAG", mGetSingleUserResponse.value.toString())
                    mGetSingleUserState.value = ApiState.Success
                }
            } catch (e: Exception) {
                mGetSingleUserState.value =  ApiState.Error
                Log.i("TAG", e.toString())
            }
        }
    }

    fun getUserList() {
        viewModelScope.launch {
            try {
                val userList = service.getUserList(2)
                Log.i("TAG", userList.body().toString())
            } catch (e: Exception) {
                Log.i("TAG", e.toString())
            }
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            try {
                service.deleteUser(2)
                Log.i("TAG", "Successful")
            } catch (e: Exception) {
                Log.i("TAG", e.toString())
            }
        }
    }
    
    fun loginUser() {
        viewModelScope.launch {
            mLoginState.value = RetrofitApiStateNormal.Loading()
            val apiResponse = apiRepo.userLogin(
                UserLogin(
                    email = "eve.holt@reqres.in",
                    password = "cityslicka"
                )
            )
            when (apiResponse) {
                is RetrofitApiStateNormal.Error -> {
                    mLoginState.value = apiResponse
                }
                is RetrofitApiStateNormal.Success<*> -> {
                    mLoginState.value = apiResponse
                }
                else -> {}
            }
        }
    }
}

sealed class RetrofitApiStateNormal {
    class Idle: RetrofitApiStateNormal()
    class Loading: RetrofitApiStateNormal()
    data class Error(val errorType: ApiErrorNormal): RetrofitApiStateNormal()
    data class Success<T>(val data: T): RetrofitApiStateNormal()
}

sealed class ApiErrorNormal {
    abstract val message: String
    data class Exception(val e: kotlin.Exception): ApiErrorNormal() {
        override val message: String
            get() = e.toString()
    }
    class ServerError: ApiErrorNormal() {
        override val message: String
            get() = "Sorry, there's some problem with the server"
    }
    class SingleUserNotFound: ApiErrorNormal() {
        override val message: String
            get() = "User with the provided id not found, please recheck the id"
    }
    class RegisterUnsuccessful(val errorMessage: String): ApiErrorNormal() {
        override val message: String
            get() = this.errorMessage
    }
    class LoginUnsuccessful(val errorMessage: String): ApiErrorNormal() {
        override val message: String
            get() = this.errorMessage
    }
}