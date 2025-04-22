package com.example.learningproject

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingPracticeViewModel: ViewModel() {
    // when the mutable live data is itself exposed
    val name = MutableLiveData<String>()

    // not exposing the MutableLiveData itself
    private val _age = MutableLiveData(15)
    val age: LiveData<Int>
        get() = _age

    fun updateAge(age: String) {
        _age.value = age.toInt()
    }

    fun addOneToAge(view: View) {
        _age.value = _age.value?.plus(1)
    }

    // now using a simple data model
    private val _person = MutableLiveData<Person>(Person("personOne", 20))
    val person : LiveData<Person>
        get() = _person

    fun updatePersonName(name: String) {
        val updatedPerson = _person.value?.copy(name = name)
        _person.value = updatedPerson!!
    }

    fun updatePersonAge(age: String) {
        if (age.isEmpty()) {
            val updatedPerson = _person.value?.copy(age = 0)
            _person.value = updatedPerson!!
        } else {
            try {
                val updatedPerson = _person.value?.copy(age = age.toInt())
                _person.value = updatedPerson!!
            } catch (e: NumberFormatException) {
                val updatedPerson = _person.value?.copy(age = 0)
                _person.value = updatedPerson!!
            }
        }
    }
}

data class Person(
    val name: String,
    val age: Int
)

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("textChange")
    fun setTextChange(editText: EditText, viewModel: DataBindingPracticeViewModel) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateAge(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    @JvmStatic
    @BindingAdapter("personEdit")
    fun changePersonDetails(editText: EditText, viewModel: DataBindingPracticeViewModel) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when (editText.id) {
                    R.id.editName -> viewModel.updatePersonName(s.toString())
                    R.id.editAge -> viewModel.updatePersonAge(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    @JvmStatic
    @BindingAdapter("app:goneUnless")
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}