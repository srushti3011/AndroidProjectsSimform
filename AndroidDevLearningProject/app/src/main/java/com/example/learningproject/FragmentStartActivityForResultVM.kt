package com.example.learningproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentStartActivityForResultVM: ViewModel() {
    private val mResult = MutableLiveData("")
    val result: LiveData<String> = mResult

    fun setResultValue(newValue: String) {
        mResult.value = newValue
    }
}