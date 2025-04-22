package com.example.learningproject

data class FeedbackResponse(
    val name: String,
    val email: String,
    val feedbackType: FeedbackType,
    val feedbackDetails: String,
    val sendResponseOnEmail: Boolean
)

enum class DETAILERROR(val message: String) {
    Name("Name is empty"),
    Email("Email not valid"),
    FeedbackDetails("Feedback detail should be atleast 10 characters")
}