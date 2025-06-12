package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.RetrofitWithInjection.UserRespository
import com.example.learningproject.RetrofitWithInjection.onError
import com.example.learningproject.RetrofitWithInjection.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailFromApiVM @Inject constructor(
    private val userRepository: UserRespository
): ViewModel() {

    private val mSingleUserState = MutableLiveData<ExerciseApiState>(ExerciseApiState.Idle())
    val singleUserState: LiveData<ExerciseApiState>
        get() = mSingleUserState

    fun getSingleUser(id: Int) {
        mSingleUserState.value = ExerciseApiState.Loading()
        viewModelScope.launch {
            userRepository.getsingleUser(id)
                .onSuccess {
                    mSingleUserState.value = ExerciseApiState.Success(it)
                    Log.i("TAG", it.toString())
                }
                .onError { code, message ->
                    mSingleUserState.value = ExerciseApiState.Error(message)
                    Log.i("TAG", message)
                }
        }
    }
}