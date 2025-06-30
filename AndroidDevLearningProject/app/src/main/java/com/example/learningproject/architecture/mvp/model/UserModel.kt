package com.example.learningproject.architecture.mvp.model

data class UserModel(
    val firstName: String,
    val lastName: String,
    val city: String
) {
    fun computeFullName(): String = "$firstName $lastName"
    override fun toString(): String {
        return "Firstname $firstName Lastname $lastName City $city"
    }
}