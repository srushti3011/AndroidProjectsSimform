package com.example.learningproject.NetworkCallFiles

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class CustomInterceptor: Interceptor {
    init {
        Log.i("TAG", "In interceptor's init")
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        var priorResponse: Response
        Log.i("TAG", "Intercepting the request")
        val requestWithHeader = chain.request().newBuilder()
            .addHeader("x-api-key", "reqres-free-v1")
            .addHeader("Content-Type", "application/json")
            .build()
        val response = chain.proceed(requestWithHeader)
        Log.i("TAG", response.code.toString())
        if (response.code in (200..299)) {
            return response
        } else {
            priorResponse = response
            val retryCount = 3
            for (i in 1..retryCount) {
                priorResponse.close()
                Log.i("TAG", "retrying for ${i}")
                val responseRetry = tryAgain(chain, requestWithHeader)
                priorResponse = responseRetry
                if (responseRetry.code in 200..299) {
                    return responseRetry
                }
            }
            return priorResponse
        }
    }

    fun tryAgain(chain: Interceptor.Chain, request: Request): Response {
        return chain.proceed(request)
    }
}