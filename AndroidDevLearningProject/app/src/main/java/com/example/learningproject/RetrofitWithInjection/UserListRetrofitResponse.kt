package com.example.learningproject.RetrofitWithInjection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListRetrofitResponse(
    @SerialName("data")
    val data: List<UserListReqres>,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("support")
    val support: Support,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
)