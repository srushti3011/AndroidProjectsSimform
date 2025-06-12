package com.example.learningproject.RetrofitWithInjection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRetrofitResponse(
    @SerialName("token")
    var token: String? = null,
    @SerialName("error")
    var error: String? = null
)

@Serializable
data class LoginUserRetrofitBody(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class ApiErrorModel(
    @SerialName("error")
    val error: String
)