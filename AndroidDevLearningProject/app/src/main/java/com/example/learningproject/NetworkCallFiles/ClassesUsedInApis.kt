package com.example.learningproject.NetworkCallFiles

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// for endpoint : /api/users/2 (GET)
@Serializable
data class SingleUserReqresResponse(
    val data: DataSingleUser,
    val support: SupportSingleUSer
)

@Serializable
data class DataSingleUser(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    var avatar: String
)

@Serializable
data class SupportSingleUSer(
    val url: String,
    val text: String
)

// for endpoint : /api/users (POST)
@Serializable
data class CreateUserBody(
    val name: String,
    val job: String
)

@Serializable
data class CreateUserResponse(
    val name: String,
    val job: String,
    val id: String,
    val createdAt: String
)

// SingleUserResponse that can be used using Gson
data class SingleUserResponseForGson(
    val data: DataForGson,
    val support: SupportForGson
)

data class DataForGson(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null,
    val avatar: String
)

data class SupportForGson(
    val url: String,
    val text: String
)

data class UserLogin(
    val email: String,
    val password: String
)

data class UserLoginResponse(
    val token: String
)

//user list -- used in retrofit
data class UserList(
    val data: Array<DataSingleUser>
)

data class LoginError(
    val error: String
)