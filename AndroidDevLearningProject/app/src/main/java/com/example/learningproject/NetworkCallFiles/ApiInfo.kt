package com.example.learningproject.NetworkCallFiles

enum class ApiState {
    Idle, Loading, Success, Error
}

enum class HTTPMethods(method: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE")
}

enum class BaseURL(val url: String) {
    reqres("https://reqres.in/api");
}

sealed class EndPoints {
    abstract val httpMethod: HTTPMethods
    abstract val baseURL: BaseURL
    // path params nullable string
    // query params nullable map

    data class GetSingleUser(val id: Int): EndPoints() {
        override val httpMethod: HTTPMethods
            get() = HTTPMethods.GET
        override val baseURL: BaseURL
            get() = BaseURL.reqres
    }

    data class GetUserList(val queryParams: Map<String, String>): EndPoints() {
        override val httpMethod: HTTPMethods
            get() = HTTPMethods.GET
        override val baseURL: BaseURL
            get() = BaseURL.reqres
    }

    class RegisterUser: EndPoints() {
        override val httpMethod: HTTPMethods
            get() = HTTPMethods.POST
        override val baseURL: BaseURL
            get() = BaseURL.reqres
    }

    class CreateUser: EndPoints() {
        override val httpMethod: HTTPMethods
            get() = HTTPMethods.POST
        override val baseURL: BaseURL
            get() = BaseURL.reqres
    }

    private fun getRoute(): String {
        return when(this) {
            is GetSingleUser, is GetUserList, is CreateUser -> "/users"
            is RegisterUser -> "/register"
        }
    }

    fun makeURL(): String {
        return when (this) {
            is GetSingleUser -> "${baseURL.url}${getRoute()}/${id}"
            is GetUserList -> "${baseURL.url}${getRoute()}${getQueryParams()}"
            is RegisterUser, is CreateUser -> "${baseURL.url}${getRoute()}"
        }
    }

    private fun getQueryParams(): String? {
        return when (this) {
            is GetSingleUser, is RegisterUser, is CreateUser -> null
            is GetUserList -> {
                var queryParamsToAppend = "?"
                queryParams.forEach {
                    queryParamsToAppend += "${it.key}=${it.value}&"
                }
                queryParamsToAppend
            }
        }
    }
}