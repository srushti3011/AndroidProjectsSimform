package com.example.learningproject.RetrofitWithInjection

interface UserRespository {
    suspend fun loginUser(body: LoginUserRetrofitBody): ApiResult<LoginUserRetrofitResponse>
}