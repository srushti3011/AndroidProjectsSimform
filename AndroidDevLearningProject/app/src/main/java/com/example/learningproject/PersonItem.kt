package com.example.learningproject

data class PersonItem(
    val id: Int,
    var name: String
)

var personData = listOf(
    PersonItem(
        id = 1,
        name = "person 1"
    ),
    PersonItem(
        id = 2,
        name = "person 2"
    )
)