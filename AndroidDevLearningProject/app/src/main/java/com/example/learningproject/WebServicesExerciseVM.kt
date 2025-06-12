package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.RetrofitWithInjection.LoginUserRetrofitBody
import com.example.learningproject.RetrofitWithInjection.UserRespository
import com.example.learningproject.RetrofitWithInjection.onError
import com.example.learningproject.RetrofitWithInjection.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebServicesExerciseVM @Inject constructor(
    private val userRepository: UserRespository
): ViewModel() {
    private val mLoginState = MutableLiveData<ExerciseApiState>(ExerciseApiState.Idle())
    val loginState: LiveData<ExerciseApiState>
        get() = mLoginState

    fun loginUser(body: LoginUserRetrofitBody) {
        Log.i("TAG", "WebServicesExerciseVM's loginUser")
        viewModelScope.launch {
            userRepository.loginUser(body)
                .onSuccess {
                    mLoginState.value = ExerciseApiState.Success(it)
                }
                .onError { code, message ->
                    mLoginState.value = ExerciseApiState.Error(message)
                }
        }
    }
}

sealed class ExerciseApiState {
    class Idle: ExerciseApiState()
    class Loading: ExerciseApiState()
    data class Error(val error: String): ExerciseApiState()
    data class Success<T>(val data: T): ExerciseApiState()
    data class ExceptionState(val excepion: Exception): ExerciseApiState()
}