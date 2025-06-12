package com.example.learningproject.RetrofitWithInjection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserRetrofitResponse(
    @SerialName("data")
    val data: UserListReqres,
    @SerialName("support")
    val support: Support
)