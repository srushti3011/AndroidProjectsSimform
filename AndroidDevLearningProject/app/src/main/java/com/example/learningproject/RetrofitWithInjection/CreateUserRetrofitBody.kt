package com.example.learningproject.RetrofitWithInjection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRetrofitBody(
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String
)

@Serializable
data class CreateUserRetrofitResponse(
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String,
    @SerialName("id")
    val id: String,
    @SerialName("createdAt")
    val createdAt: String
)