package com.example.learningproject.NetworkCallFiles

import android.util.Log
import com.example.learningproject.ApiErrorNormal
import com.example.learningproject.RetrofitApiStateNormal
import com.google.gson.Gson

class ApiRepository {
    suspend fun userLogin(body: UserLogin): RetrofitApiStateNormal {
        try {
            val response = RetrofitInstance.apiInterface.loginUser(body)
            if (response.isSuccessful) { // code between 200..299
                response.body()?.let {
                    Log.i("TAG", response.body().toString())
                    return RetrofitApiStateNormal.Success(response.body().toString())
                }
            } else {
                if (response.code() in 400..499) {
                    return RetrofitApiStateNormal.Error(
                        ApiErrorNormal.LoginUnsuccessful(
                            Gson().fromJson(response.errorBody()?.string(),
                            LoginError::class.java).error
                        )
                    )
                } else if (response.code() in 500..599) {
                    return RetrofitApiStateNormal.Error(
                        ApiErrorNormal.ServerError()
                    )
                }
                response.errorBody()?.string()?.let { Log.i("TAG", it) }
            }
        } catch (e: Exception) {
            return RetrofitApiStateNormal.Error(
                ApiErrorNormal.Exception(e)
            )
        }
        return RetrofitApiStateNormal.Error(
            ApiErrorNormal.Exception(Exception("Unknown error"))
        )
    }
}