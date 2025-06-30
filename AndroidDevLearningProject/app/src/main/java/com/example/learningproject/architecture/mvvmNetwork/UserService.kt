package com.example.learningproject.architecture.mvvmNetwork

import android.util.Log
import kotlinx.coroutines.delay

class UserService {
    companion object {
        val shared = UserService()
    }
    suspend fun userSignup(user: SignUpDetailsModel): Boolean {
        Log.i("TAG", "creating the user")
        delay(3)
        if ((1..100).random() % 2 == 0) {
            return true
        } else {
            return false
        }
    }
}