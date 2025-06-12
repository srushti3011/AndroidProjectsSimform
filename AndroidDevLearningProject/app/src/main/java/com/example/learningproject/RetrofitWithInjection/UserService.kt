package com.example.learningproject.RetrofitWithInjection

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
//    @Headers("x-api-key: reqres-free-v1", "Content-Type: application/json")
    @POST("login")
    suspend fun loginUser(
        @Body body: LoginUserRetrofitBody
    ): ApiResult<LoginUserRetrofitResponse>
}