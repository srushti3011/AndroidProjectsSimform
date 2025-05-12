package com.example.learningproject

data class Movies(
    val name: String,
    val description: String,
    var isVisible: Boolean = false
)

data class MoviesForList(
    val name: String,
    val description: Array<String>
)