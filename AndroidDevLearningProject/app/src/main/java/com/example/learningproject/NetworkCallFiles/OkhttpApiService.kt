package com.example.learningproject.NetworkCallFiles

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class OkhttpApiService {

    companion object {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    suspend inline fun <reified T> callWithoutBody(
        endPoints: EndPoints
    ): Result<T> = withContext(Dispatchers.IO) {
        try {
            val httpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("reqres.in")
                .addPathSegment("api")
                .addPathSegment("users")
                .addPathSegment("2")
                .build()
            Log.i("URL", httpUrl.toUrl().toString())
            val request = Request.Builder()
                .url(httpUrl)
                .addHeader("x-api-key", "reqres-free-v1")
                .addHeader("Content-Type", "application/json")
                .build()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.i("TAG", "not successful")
                return@withContext Result.failure(Exception("API call failed with code ${response.code}"))
            }
            val body = response.body?.string()
            if (body != null) {
                Log.i("TAG", body)
                val responseBody = Gson().fromJson(body, T::class.java)
                return@withContext Result.success(responseBody)
            }
            return@withContext Result.failure(Exception("Some problem"))
        } catch (e: Exception) {
            Log.i("CATCH", e.toString())
            return@withContext Result.failure(e)
        }
    }

    //    T -> generic type for response to be received
    suspend inline fun <reified T> makeCallWithoutBody(
        endpoint: OkhttpApiInfoEndpoints
    ): Result<T> = withContext(Dispatchers.IO) {
        try {
            val request = endpoint.buildRequest()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.i("TAG", "not successful")
                return@withContext Result.failure(Exception("API call failed with code ${response.code}"))
            }
            val body = response.body?.string()
            if (body != null) {
                if (!body.isEmpty()) {
                    Log.i("TAG", body)
                }
                val responseBody = Gson().fromJson(body, T::class.java)
                return@withContext Result.success(responseBody)
            }
            return@withContext Result.failure(Exception("something went wrong"))
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    //    T -> generic type for response to be received
    //    and
    //    U -> generic type for request body to be sent
    suspend inline fun <reified T, reified U> makeCallWithBody(
        endpoint: OkhttpApiInfoEndpoints,
        body: U
    ): Result<T> = withContext(Dispatchers.IO) {
        try {
            val bodyToBeSent = Gson().toJson(body)
            val request = endpoint.buildRequest(bodyToBeSent.toRequestBody())
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.i("PROBLEM", "not successful, failed with ${response.code}")
                return@withContext Result.failure(Exception("API call failed with code ${response.code}"))
            }
            val bodyReceived = response.body?.string()
            if (bodyReceived != null) {
                Log.i("TAG", bodyReceived)
                val responseBody = Gson().fromJson(bodyReceived, T::class.java)
                return@withContext Result.success(responseBody)
            }
            return@withContext Result.failure(Exception("something went wrong"))
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }
}