package com.example.learningproject.NetworkCallFiles

import android.util.Log
import com.example.learningproject.ApiError
import com.example.learningproject.RetrofitApiState
import com.google.gson.Gson

class ApiRepository {
    suspend fun userLogin(body: UserLogin): RetrofitApiState {
        try {
            val response = RetrofitInstance.apiInterface.loginUser(body)
            if (response.isSuccessful) { // code between 200..299
                response.body()?.let {
                    Log.i("TAG", response.body().toString())
                    return RetrofitApiState.Success(response.body().toString())
                }
            } else {
                if (response.code() in 400..499) {
                    return RetrofitApiState.Error(
                        ApiError.LoginUnsuccessful(
                            Gson().fromJson(response.errorBody()?.string(),
                            LoginError::class.java).error
                        )
                    )
                } else if (response.code() in 500..599) {
                    return RetrofitApiState.Error(
                        ApiError.ServerError()
                    )
                }
                response.errorBody()?.string()?.let { Log.i("TAG", it) }
            }
        } catch (e: Exception) {
            return RetrofitApiState.Error(
                ApiError.Exception(e)
            )
        }
        return RetrofitApiState.Error(
            ApiError.Exception(Exception("Unknown error"))
        )
    }
}