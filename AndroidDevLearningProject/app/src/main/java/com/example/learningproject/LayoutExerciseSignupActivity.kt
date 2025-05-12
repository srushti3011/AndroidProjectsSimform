package com.example.learningproject

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LayoutExerciseSignupActivity : AppCompatActivity() {

    private var inputUserName: EditText? = null
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout_exercise_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputUserName = findViewById(R.id.inputUserName)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)

        inputUserName?.setOnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN)) {
                inputEmail?.requestFocus()
            }
            return@setOnEditorActionListener true
        }

        inputEmail?.setOnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN)) {
                inputPassword?.requestFocus()
            }
            return@setOnEditorActionListener true
        }
        changeColor()
        val togglePasswordImage = findViewById<ImageView>(R.id.togglePasswordImage)
        togglePasswordImage.setOnClickListener {
            togglePassVisability(inputPassword!!, togglePasswordImage)
        }
    }

    private fun changeColor() {
        val tvHaveAccount = findViewById<TextView>(R.id.tvHaveAccount)
        val spannableStringValue = SpannableString(tvHaveAccount.text)
        val text = tvHaveAccount.text
//        resources.getColor(R.color.inputEmail)
        val color = ContextCompat.getColor(this, R.color.layout_exe_button_color)
        val colorToApply = ForegroundColorSpan(color)
        val indexStart = text.indexOf("Signup")
        val indexEnd = indexStart + "Signup".length
        spannableStringValue.setSpan(colorToApply, indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvHaveAccount.text = spannableStringValue

        val tvTosAndPriPol = findViewById<TextView>(R.id.tvTosAndPriPol)
        val spannableStringTOS = SpannableString(tvTosAndPriPol.text)
        val textOne = tvTosAndPriPol.text
        val spanForPrivacyPolicy = ForegroundColorSpan(color)
        val indStartTermsOfService = textOne.indexOf("Terms of Service")
        val indexTOSEnd = indStartTermsOfService + "Terms of Service".length
        spannableStringTOS.setSpan(colorToApply, indStartTermsOfService, indexTOSEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val indexStartPriPol = textOne.indexOf("Privacy Policy")
        val indexEndPriPol = indexStartPriPol + "Privacy Policy".length
        spannableStringTOS.setSpan(spanForPrivacyPolicy, indexStartPriPol, indexEndPriPol, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvTosAndPriPol.text = spannableStringTOS
    }

    private fun togglePassVisability(firstEditText: EditText, imgView: ImageView) {
        val pass: String = firstEditText.getText().toString()
        if (isPasswordVisible) {
            imgView.setImageResource(R.drawable.eye_open)
            firstEditText.setTransformationMethod(PasswordTransformationMethod.getInstance())
        } else {
            imgView.setImageResource(R.drawable.eye_slash_svgrepo_com)
            firstEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
        }
        firstEditText.setText(pass)
        firstEditText.setSelection(pass.length)
        isPasswordVisible = !isPasswordVisible
    }
}