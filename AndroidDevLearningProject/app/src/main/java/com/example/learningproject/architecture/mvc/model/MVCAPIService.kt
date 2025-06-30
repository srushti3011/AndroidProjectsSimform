package com.example.learningproject.architecture.mvc.model

class MVCAPIService {

    companion object {
        val shared = MVCAPIService()
    }

    fun getNumber(): NumberModel {
        return NumberModel(
            number = (0..200).random()
        )
    }
}