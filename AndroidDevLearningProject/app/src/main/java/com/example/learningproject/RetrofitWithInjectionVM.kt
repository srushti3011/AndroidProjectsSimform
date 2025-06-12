package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.RetrofitWithInjection.LoginUserRetrofitBody
import com.example.learningproject.RetrofitWithInjection.UserRespository
import com.example.learningproject.RetrofitWithInjection.onError
import com.example.learningproject.RetrofitWithInjection.onException
import com.example.learningproject.RetrofitWithInjection.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class RetrofitStateAPI {
    Idle, Loading, Succcess, Error
}

@HiltViewModel
class RetrofitWithInjectionVM @Inject constructor(
    private val userRepository: UserRespository
) : ViewModel() {

    private val mApiState = MutableLiveData(RetrofitStateAPI.Idle)
    val apiState: LiveData<RetrofitStateAPI>
        get() = mApiState

    fun loginUser() {
        mApiState.value = RetrofitStateAPI.Loading
        Log.i("TAG", "Inside loginUser of viewmodel")
        viewModelScope.launch {
            userRepository.loginUser(
                body = LoginUserRetrofitBody(
                    email = "eve.holt@reqre.in",
                    password = "cityslcka"
                )
            )
                .onError { code, message ->
                    mApiState.value = RetrofitStateAPI.Error
                    Log.i("TAG", message)
                }
                .onSuccess {
                    mApiState.value = RetrofitStateAPI.Succcess
                    Log.i("TAG", it.toString())
                }
                .onException { Log.i("TAG", it.toString()) }
        }
    }
}