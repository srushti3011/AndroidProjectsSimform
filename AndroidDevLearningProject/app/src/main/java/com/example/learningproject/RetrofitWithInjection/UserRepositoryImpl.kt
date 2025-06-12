package com.example.learningproject.RetrofitWithInjection

import android.util.Log
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRespository {
    override suspend fun loginUser(body: LoginUserRetrofitBody): ApiResult<LoginUserRetrofitResponse>
    {
        Log.i("TAG", "inside UserRepositoryImpl's loginUser")
        return userService.loginUser(body)
    }

    override suspend fun getUserList(pageNo: Int): ApiResult<UserListRetrofitResponse> {
        Log.i("TAG", "inside UserRepositoryImpl's getUserList")
        return userService.getUserList(pageNo)
    }

    override suspend fun getsingleUser(userId: Int): ApiResult<SingleUserRetrofitResponse> {
        return userService.getSingleUser(userId)
    }

    override suspend fun createUser(body: CreateUserRetrofitBody): ApiResult<CreateUserRetrofitResponse> {
        return userService.createUser(body)
    }
}