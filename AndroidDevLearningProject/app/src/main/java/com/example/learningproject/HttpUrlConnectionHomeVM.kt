package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.NetworkCallFiles.ApiCallService
import com.example.learningproject.NetworkCallFiles.ApiState
import com.example.learningproject.NetworkCallFiles.CreateUserBody
import com.example.learningproject.NetworkCallFiles.CreateUserResponse
import com.example.learningproject.NetworkCallFiles.EndPoints
import com.example.learningproject.NetworkCallFiles.SingleUserReqresResponse
import kotlinx.coroutines.launch

class HttpUrlConnectionHomeVM : ViewModel() {
    companion object {
        private val apiCaller = ApiCallService()
    }

    private var mApiResponse = MutableLiveData<SingleUserReqresResponse>()
    val apiResponse: LiveData<SingleUserReqresResponse>
        get() = mApiResponse

    private var mApiError = MutableLiveData<Throwable>()
    val apiError: LiveData<Throwable>
        get() = mApiError

    private var mApiState = MutableLiveData(ApiState.Idle)
    val apiState: LiveData<ApiState>
        get() = mApiState

    private var onGoingCalls: MutableList<String> = mutableListOf()

    private var mCreateUserResponse = MutableLiveData<CreateUserResponse>()
    val createUserResponse: LiveData<CreateUserResponse>
        get() = mCreateUserResponse

    private var mCreateUserError = MutableLiveData<Throwable>()
    val createUserError: LiveData<Throwable>
        get() = mCreateUserError

    private var mCreateUserApiState = MutableLiveData(ApiState.Idle)
    val createUserApiState: LiveData<ApiState>
        get() = mCreateUserApiState

    fun makeCoroutineGetCall() {
        if (onGoingCalls.contains("makeCoroutineGetCall")) {
            Log.i("TAG", "not firing again as already running")
            return
        }
        viewModelScope.launch {
            onGoingCalls.add("makeCoroutineGetCall")
            mApiState.value = ApiState.Loading
            val endPoint = EndPoints.GetSingleUser(
                id = 2
            )
            apiCaller.callApiWithoutBody<SingleUserReqresResponse>(
                reqMethod = endPoint.httpMethod.toString(),
                headers = mapOf(
                        "x-api-key" to "reqres-free-v1"
                    ),
                urlString = endPoint.makeURL()
            )
                .onSuccess {
                    mApiResponse.value = it
                    Log.i("TAG", it.toString())
                    mApiState.value = ApiState.Success

                }
                .onFailure {
                    mApiError.value = it
                    mApiState.value = ApiState.Error
                }
        }
    }

    fun createUser() {
        if (onGoingCalls.contains("createUser")) {
            Log.i("TAG", "not firing createUser again as it is already running")
            return
        }
        viewModelScope.launch {
            onGoingCalls.add("createUser")
            mCreateUserApiState.value = ApiState.Loading
            val endPoint = EndPoints.CreateUser()
            apiCaller.callWithBody<CreateUserBody, CreateUserResponse>(
                reqMethod = endPoint.httpMethod.toString(),
                headers = mapOf(
                    "x-api-key" to "reqres-free-v1",
                    "Content-Type" to "application/json"
                ),
                body = CreateUserBody(
                    name = "NewUserName",
                    job = "NewUserJob"
                ),
                urlString = endPoint.makeURL()
            )
                .onSuccess {
                    mCreateUserResponse.value = it
                    mCreateUserApiState.value = ApiState.Success
                }
                .onFailure {
                    mCreateUserError.value = it
                    mCreateUserApiState.value = ApiState.Error
                }
        }
    }

    fun callComplete(funName: String) {
        onGoingCalls.remove(funName)
    }
}