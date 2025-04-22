package com.example.learningproject

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WidgetExerciseViewModel: ViewModel() {
    private val mResponse = MutableLiveData(
        FeedbackResponse(
            name = "",
            "",
            FeedbackType.PRAISE,
            "",
            true
        )
    )
    val response: LiveData<FeedbackResponse>
        get() = mResponse

    fun setName(nameReceived: String) {
        val updatedResponse = mResponse.value?.copy(name = nameReceived)
        mResponse.value = updatedResponse
    }

    fun setEmail(emailReceived: String) {
        val updatedResponse = mResponse.value?.copy(email = emailReceived)
        mResponse.value = updatedResponse
    }

    fun setFeedBackType(selection: String) {
        Log.i("setFeedBackType", selection)
        try {
            val enumRef = FeedbackType.valueOf(selection.uppercase())
            val updatedResponse = mResponse.value?.copy(feedbackType = enumRef)
            mResponse.value = updatedResponse
        } catch (e: IllegalArgumentException) {
            val enumRef = FeedbackType.valueOf(selection)
            val updatedResponse = mResponse.value?.copy(feedbackType = FeedbackType.PRAISE)
            mResponse.value = updatedResponse
        }
    }

    fun setFeedBackDetails(detailReceived: String) {
        val updatedResponse = mResponse.value?.copy(feedbackDetails = detailReceived)
        mResponse.value = updatedResponse
    }

    fun setSendResponseOnEmail() {
        val updatedResponse = mResponse.value?.copy(
            sendResponseOnEmail = mResponse.value?.sendResponseOnEmail!!.not()
        )
        mResponse.value = updatedResponse
    }

    val listFeedBackTypes = listOf("Praise", "Good", "Bad")

    fun isEmailValid(emailId: CharSequence): Boolean {
        if (TextUtils.isEmpty(emailId)) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
        }
    }
}