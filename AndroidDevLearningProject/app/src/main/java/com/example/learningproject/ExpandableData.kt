package com.example.learningproject

data class ExpandableData(
    val image: Int,
    val title: String,
    val description: String,
    var isExpanded: Boolean
)

val expandableDataCollection = arrayOf(
    ExpandableData(
        image = R.drawable.baseline_call_24,
        title = "Call",
        description = "Call description",
        isExpanded = false,
    ),
    ExpandableData(
        image = R.drawable.baseline_account_circle_24,
        title = "Account",
        description = "Account description",
        isExpanded = false
    ),
    ExpandableData(
        image = R.drawable.carrot,
        title = "Carrot",
        description = "It is a vegetable",
        isExpanded = false
    ),
    ExpandableData(
        image = R.drawable.location_icon,
        title = "Location",
        description = "It is a place",
        isExpanded = false
    ),
    ExpandableData(
        image = R.drawable.carrot,
        title = "Facebook",
        description = "It is a social media platform",
        isExpanded = false
    ),
    ExpandableData(
        image = R.drawable.carrot,
        title = "Google",
        description = "It is a search engine",
        isExpanded = false
    )
)