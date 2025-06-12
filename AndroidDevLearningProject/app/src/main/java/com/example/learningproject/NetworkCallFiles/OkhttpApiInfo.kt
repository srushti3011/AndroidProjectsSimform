package com.example.learningproject.NetworkCallFiles

import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody

sealed class OkhttpApiInfoEndpoints {
    val initialPathSegment = "api"
    val universalHeaders = mapOf(
        "x-api-key" to "reqres-free-v1",
        "Content-Type" to "application/json"
    )

    data class listOfUsers(val page: Int): OkhttpApiInfoEndpoints()
    data class getSingleUser(val id: Int): OkhttpApiInfoEndpoints()
    class createUser(): OkhttpApiInfoEndpoints()
    data class updateUser(val id: Int): OkhttpApiInfoEndpoints()
    data class deleteUser(val id: Int): OkhttpApiInfoEndpoints()
    class registerUser(): OkhttpApiInfoEndpoints()
    class loginUser(): OkhttpApiInfoEndpoints()

    fun getAllPathSegments(): Array<String> {
        return when (this) {
            is listOfUsers,
            is createUser,
            is loginUser,
            is registerUser -> arrayOf(initialPathSegment, this.getRoute())
            is getSingleUser -> arrayOf(initialPathSegment, this.getRoute(), this.id.toString())
            is updateUser -> arrayOf(initialPathSegment, this.getRoute(), this.id.toString())
            is deleteUser -> arrayOf(initialPathSegment, this.getRoute(), this.id.toString())
        }
    }

    fun getRoute(): String {
        return when (this) {
            is listOfUsers,
            is createUser,
            is getSingleUser ,
            is updateUser,
            is deleteUser -> "users"
            is loginUser -> "login"
            is registerUser -> "register"
        }
    }

    fun getAllQueryParams(): Map<String, String>? {
        return when (this) {
            is listOfUsers -> mapOf("page" to this.page.toString())
            else -> null
        }
    }

    @Throws(Exception::class)
    fun buildHTTPUrl(): HttpUrl {
        try {
            return when (this) {
                else -> {
                    val httpUrl = HttpUrl.Builder()
                        .scheme("https")
                        .host("reqres.in")
                    val pathParams = this.getAllPathSegments()
                    pathParams.forEach { httpUrl.addPathSegment(it) }
                    val queryParams = this.getAllQueryParams()
                    if (queryParams != null) {
                        queryParams.keys.toTypedArray().forEach {
                            httpUrl.addQueryParameter(it, queryParams.get(it))
                        }
                    }
                    return httpUrl.build()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    fun buildRequest(body: RequestBody? = null): Request {
        try {
            val request = Request.Builder()
            return when (this) {
                is createUser, is registerUser, is loginUser -> {
                    if (body != null) {
                        request.url(this.buildHTTPUrl())
                            .post(body)
                    }
                    universalHeaders.keys.toTypedArray().forEach { universalHeaders.get(it)
                        ?.let { it1 -> request.addHeader(it, it1) } }
                    return  request.build()
                }

                is deleteUser -> {
                    request.url(this.buildHTTPUrl())
                        .delete()
                    universalHeaders.keys.toTypedArray().forEach { universalHeaders.get(it)
                        ?.let { it1 -> request.addHeader(it, it1) } }
                    return  request.build()
                }

                is getSingleUser, is listOfUsers -> {
                    request.url(this.buildHTTPUrl())
                        .get()
                    universalHeaders.keys.toTypedArray().forEach { universalHeaders.get(it)
                        ?.let { it1 -> request.addHeader(it, it1) } }
                    return request.build()
                }

                is updateUser -> {
                    if (body != null) {
                        request.url(this.buildHTTPUrl())
                            .put(body)
                    }
                    universalHeaders.keys.toTypedArray().forEach { universalHeaders.get(it)
                        ?.let { it1 -> request.addHeader(it, it1) } }
                    return  request.build()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}