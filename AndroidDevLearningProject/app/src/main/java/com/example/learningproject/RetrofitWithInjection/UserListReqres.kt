package com.example.learningproject.RetrofitWithInjection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListReqres(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("last_name")
    val lastName: String
)