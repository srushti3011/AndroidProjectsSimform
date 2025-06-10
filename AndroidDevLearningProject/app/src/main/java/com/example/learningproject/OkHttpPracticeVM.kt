package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.NetworkCallFiles.ApiState
import com.example.learningproject.NetworkCallFiles.EndPoints
import com.example.learningproject.NetworkCallFiles.OkhttpApiInfoEndpoints
import com.example.learningproject.NetworkCallFiles.OkhttpApiService
import com.example.learningproject.NetworkCallFiles.SingleUserReqresResponse
import com.example.learningproject.NetworkCallFiles.SingleUserResponseForGson
import com.example.learningproject.NetworkCallFiles.UserLoginResponse
import com.example.learningproject.NetworkCallFiles.UserLogin
import kotlinx.coroutines.launch

class OkHttpPracticeVM : ViewModel() {
    companion object {
        val apiCaller = OkhttpApiService()
    }

    private var mSingleUserResponse = MutableLiveData<SingleUserResponseForGson>()
    val singleUserResponse: LiveData<SingleUserResponseForGson>
        get() = mSingleUserResponse

    private var mSingleUserState = MutableLiveData<ApiState>(ApiState.Idle)
    val singleUserState: LiveData<ApiState>
        get() = mSingleUserState

    private var mLoginUserState = MutableLiveData<ApiState>(ApiState.Idle)
    val loginUserstate: LiveData<ApiState>
        get() = mLoginUserState

    private var mLoginResponse = MutableLiveData<UserLoginResponse>()
    val loginResponse: LiveData<UserLoginResponse>
        get() = mLoginResponse

    fun getSingleUser() {
        viewModelScope.launch {
            mSingleUserState.value = ApiState.Loading
            apiCaller.makeCallWithoutBody<SingleUserResponseForGson>(
                OkhttpApiInfoEndpoints.getSingleUser(
                    2
                )
            )
                .onSuccess {
                    mSingleUserResponse.value = it
                    mSingleUserState.value = ApiState.Success
                    Log.i("SUCCESS_VM", it.toString())
                }
                .onFailure {
                    mSingleUserState.value = ApiState.Error
                    Log.i("FAILURE_VM", it.toString())
                }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            mLoginUserState.value = ApiState.Loading
            val body = UserLogin(
                email = "eve.holt@reqres.in",
                password = "cityslicka"
            )
            apiCaller.makeCallWithBody<UserLoginResponse, UserLogin>(
                OkhttpApiInfoEndpoints.loginUser(),
                body = body
            )
                .onSuccess {
                    mLoginResponse.value = it
                    mLoginUserState.value = ApiState.Success
                }
                .onFailure {
                    mLoginUserState.value = ApiState.Error
                }
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            apiCaller.makeCallWithoutBody<Any>(OkhttpApiInfoEndpoints.deleteUser(2))
                .onSuccess {
                    Log.i("DELETION", "Done!")
                }
                .onFailure {
                    Log.i("DELETION", "Not deleted!")
                }
        }
    }
}