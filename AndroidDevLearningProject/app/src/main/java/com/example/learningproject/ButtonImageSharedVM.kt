package com.example.learningproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ButtonImageSharedVM: ViewModel() {
    private var mSelectedButton = MutableLiveData(SelectedButton.CALL)
    val selectedButton: LiveData<SelectedButton> = mSelectedButton

    fun setSelectedButton(selectedB: SelectedButton) {
        mSelectedButton.value = selectedB
    }
}