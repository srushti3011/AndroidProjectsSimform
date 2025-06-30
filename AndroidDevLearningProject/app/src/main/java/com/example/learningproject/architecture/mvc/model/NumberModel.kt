package com.example.learningproject.architecture.mvc.model

data class NumberModel(
    val number: Int
) {
    fun divideByTwo(): Double = number/2.0
}