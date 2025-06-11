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
}