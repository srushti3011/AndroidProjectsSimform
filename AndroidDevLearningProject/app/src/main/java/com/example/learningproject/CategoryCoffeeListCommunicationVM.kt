package com.example.learningproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryCoffeeListCommunicationVM: ViewModel() {
    private val mSelectedCategory = MutableLiveData(0)
    val selectedCategory: LiveData<Int>
        get() = mSelectedCategory

    private val mSelectedCoffee = MutableLiveData<Int>()
    val selectedCoffee: LiveData<Int>
        get() = mSelectedCoffee

    fun updateSelectedCategory(selectedCatIndex: Int) {
        mSelectedCategory.value = selectedCatIndex
        Log.i("categoryChanged", selectedCatIndex.toString())
    }

    fun getSelectedCategory(): Int {
        return selectedCategory.value ?: 0
    }

    fun updateSelectedCoffee(selectedCoffeeIndex: Int) {
        mSelectedCoffee.value = selectedCoffeeIndex
        Log.i("categoryChanged", selectedCoffeeIndex.toString())
    }
}