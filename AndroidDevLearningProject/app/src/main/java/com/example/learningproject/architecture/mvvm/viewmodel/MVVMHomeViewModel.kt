package com.example.learningproject.architecture.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.architecture.mvvmNetwork.SignUpDetailsModel
import com.example.learningproject.architecture.mvvmNetwork.UserService
import kotlinx.coroutines.launch

class MVVMHomeViewModel : ViewModel() {

    private val mUserCreated = MutableLiveData<Boolean>()
    val userCreated: LiveData<Boolean>
        get() = mUserCreated

    fun callUserSignup(user: SignUpDetailsModel) {
        viewModelScope.launch {
            if (UserService.shared.userSignup(user)) {
                setUserCreatedState(true)
            } else {
                setUserCreatedState(false)
            }
        }
    }

    fun setUserCreatedState(bool: Boolean) {
        mUserCreated.value = bool
    }
}