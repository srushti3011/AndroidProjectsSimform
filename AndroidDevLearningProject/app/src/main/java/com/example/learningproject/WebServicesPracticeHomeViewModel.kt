package com.example.learningproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.NetworkCallFiles.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebServicesPracticeHomeViewModel: ViewModel() {
    fun makeParallelCalls() {
        viewModelScope.launch {
            val parallelCalls = listOf(
                async { RetrofitInstance.apiInterface.getSingleUser(2) },
                async { RetrofitInstance.apiInterface.getUserList(2) },
                async { RetrofitInstance.apiInterface.delayedResponse(3) }
            )
            val responses = parallelCalls.awaitAll()
            responses.forEach { Log.i("TAG", it.body().toString()) }
        }
    }

    fun makeSequentialCalls() {
        viewModelScope.launch {
            val firstCall = async { RetrofitInstance.apiInterface.delayedResponse(3) }
            val firstResponse = firstCall.await()
            Log.i("TAG", firstResponse.body().toString())

            val secondResponse = async { RetrofitInstance.apiInterface.delayedResponse(5) }.await()
            Log.i("TAG", secondResponse.body().toString())
        }
    }
}