package com.example.learningproject.RetrofitWithInjection

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("login")
    suspend fun loginUser(
        @Body body: LoginUserRetrofitBody
    ): ApiResult<LoginUserRetrofitResponse>

    @GET("users")
    suspend fun getUserList(
        @Query("page") pageNo: Int
    ): ApiResult<UserListRetrofitResponse>

    @GET("users/{id}")
    suspend fun getSingleUser(
        @Path("id") userId: Int
    ): ApiResult<SingleUserRetrofitResponse>

    @POST("users")
    suspend fun createUser(
        @Body body: CreateUserRetrofitBody
    ): ApiResult<CreateUserRetrofitResponse>
}