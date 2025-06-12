package com.example.learningproject.NetworkCallFiles

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("TAG", "Intercepting the request")
        val response = chain.proceed(chain.request())
        Log.i("TAG", "After the request has been intercepted")
        return response
    }
}