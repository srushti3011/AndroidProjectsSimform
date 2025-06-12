package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.RetrofitWithInjection.CreateUserRetrofitBody
import com.example.learningproject.RetrofitWithInjection.UserRespository
import com.example.learningproject.RetrofitWithInjection.onError
import com.example.learningproject.RetrofitWithInjection.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListVM @Inject constructor(
    private val userRepository: UserRespository
): ViewModel() {

    private val mUserListState = MutableLiveData<ExerciseApiState>(ExerciseApiState.Idle())
    val userListState: LiveData<ExerciseApiState>
        get() = mUserListState

    fun getListOfUsers() {
        mUserListState.value = ExerciseApiState.Loading()
        viewModelScope.launch {
            userRepository.getUserList(2)
                .onSuccess {
                    mUserListState.value = ExerciseApiState.Success(it)
                    Log.i("TAG", it.data.toString())
                }
                .onError { code, message ->
                    Log.i("TAG", message)
                }
        }
    }

    fun createUser() {
        viewModelScope.launch {
            userRepository.createUser(
                CreateUserRetrofitBody(
                    name = "SampleName",
                    job = "SampleJob"
                )
            )
                .onSuccess {
                    Log.i("TAG", it.toString())
                }
                .onError { code, message ->
                    Log.i("TAG", message)
                }
        }
    }
}