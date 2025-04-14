package com.example.learningproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.learningproject.databinding.ActivityWidgetsExerciseBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class WidgetsExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetsExerciseBinding
    private val viewModel: WidgetExerciseViewModel by viewModels()
    private var noOfTimesSelected: Int = 0
    private val errorsInInput = mutableListOf<DETAILERROR>()
    private var nameEntered = ""
    private var emailEntered = ""
    private var feedbackDetailEntered = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWidgetsExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.checkBoxSendResponse.isChecked = viewModel.response.value?.sendResponseOnEmail ?: false
        setSpinner()

        binding.tietName.addTextChangedListener {
            nameEntered = it.toString()
        }

        binding.tietEmail.addTextChangedListener {
            emailEntered = it.toString()
        }

        binding.feedbackSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Log.i("Selection", "onItemSelected invoked")
                val index = parent?.selectedItemPosition
                val selectedString = viewModel.listFeedBackTypes.get(index ?: 0)
                viewModel.setFeedBackType(selectedString)
                if(noOfTimesSelected > 0) {
                    binding.tietFeedbackDetail.requestFocus()
                    binding.tietFeedbackDetail.postDelayed({
                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.showSoftInput(binding.tietFeedbackDetail, InputMethodManager.SHOW_IMPLICIT)
                    }, 100)
                }
                noOfTimesSelected = 1
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.tietFeedbackDetail.addTextChangedListener {
            feedbackDetailEntered = it.toString()
        }

        binding.checkBoxSendResponse.setOnCheckedChangeListener { group, checkedId ->
            viewModel.setSendResponseOnEmail()
        }

        Log.i("InitialValue", "sendResponseOnEmail: ${viewModel.response.value?.sendResponseOnEmail}")

        binding.tietEmail.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                binding.feedbackSpinner.performClick()
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }

        binding.btnSendResponse.setOnClickListener {
            if (errorInDetails()) {
                when (errorsInInput.get(0)) {
                    DETAILERROR.Name -> {
                        setErrorAndFocus(binding.tilName, binding.tietName, DETAILERROR.Name.message)
                    }
                    DETAILERROR.Email -> {
                        setErrorAndFocus(binding.tilEmail, binding.tietEmail, DETAILERROR.Email.message)
                    }
                    DETAILERROR.FeedbackDetails -> {
                        setErrorAndFocus(binding.tilFeedback, binding.tietFeedbackDetail, DETAILERROR.FeedbackDetails.message)
                    }
                }
            } else {
                if (viewModel.response.value?.sendResponseOnEmail ?: false) {
                    Toast.makeText(this, "Feedback submitted successfully and mail sent", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setErrorAndFocus(textInputL: TextInputLayout, textInputET: TextInputEditText, message: String ) {
        textInputL.error = message
        textInputET.requestFocus()
        textInputET.postDelayed({
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(textInputET, InputMethodManager.SHOW_IMPLICIT)
        }, 100)
    }

    private fun setSpinner() {
        val listFeedBackTypes = listOf("Praise", "Good", "Bad")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listFeedBackTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.feedbackSpinner.adapter = adapter
    }

    private fun errorInDetails(): Boolean {
        binding.tilEmail.error = null
        binding.tilFeedback.error = null
        binding.tilName.error = null
        var errorPresent = false
        errorsInInput.clear()
        if (nameEntered.isEmpty()) {
            errorsInInput.add(DETAILERROR.Name)
            binding.tilName.error = DETAILERROR.Name.message
            errorPresent = true
        } else {
            viewModel.setName(nameEntered)
        }
        if (!viewModel.isEmailValid(emailEntered)) {
            errorsInInput.add(DETAILERROR.Email)
            binding.tilEmail.error = DETAILERROR.Email.message
            errorPresent = true
        } else {
            viewModel.setEmail(emailEntered)
        }
        if (feedbackDetailEntered.count() < 10) {
            errorsInInput.add(DETAILERROR.FeedbackDetails)
            binding.tilFeedback.error = DETAILERROR.FeedbackDetails.message
            errorPresent = true
        } else {
            viewModel.setFeedBackDetails(feedbackDetailEntered)
        }
        return errorPresent
    }
}