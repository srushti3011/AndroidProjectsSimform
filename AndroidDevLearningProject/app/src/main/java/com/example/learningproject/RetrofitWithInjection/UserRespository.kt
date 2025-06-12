package com.example.learningproject.RetrofitWithInjection

interface UserRespository {
    suspend fun loginUser(body: LoginUserRetrofitBody): ApiResult<LoginUserRetrofitResponse>
    suspend fun getUserList(pageNo: Int): ApiResult<UserListRetrofitResponse>
    suspend fun getsingleUser(userId: Int): ApiResult<SingleUserRetrofitResponse>
    suspend fun createUser(body: CreateUserRetrofitBody): ApiResult<CreateUserRetrofitResponse>
}