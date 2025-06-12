package com.example.learningproject.NetworkCallFiles

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users/{id}")
    suspend fun getSingleUser(
        @Path("id") userId: Int
    ): Response<SingleUserResponseForGson>

    @GET("users")
    suspend fun getUserList(
        @Query("page") pageNo: Int
    ): Response<UserList>

    @Headers("x-api-key: reqres-free-v1", "Content-Type: application/json")
    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") userId: Int
//        ,
//        @Header("x-api-key") key: String,
//        @Header("Content-Type") contentType: String
//        ,
//        @HeaderMap headerMap: Map<String, String>
    ): Response<Unit>

    @Headers("x-api-key: reqres-free-v1", "Content-Type: application/json")
    @POST("login")
    suspend fun loginUser(
        @Body userLoginDetails: UserLogin
    ): Response<UserLoginResponse>
}