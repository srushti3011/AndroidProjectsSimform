package com.example.learningproject.architecture.mvp.model

class MVPAPIService {

    companion object {
        val shared = MVPAPIService()
    }

    fun getUser(): UserModel = UserModel(
        firstName = "DummyFirstName",
        lastName = "DummyLastName",
        city = "Ahmedabad"
    )
}