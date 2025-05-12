package com.example.learningproject

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LayoutExerciseLoginActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout_exercise_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val passEditText = findViewById<EditText>(R.id.inputPassword)
        val togglePasswordImage = findViewById<ImageView>(R.id.togglePasswordImage)
        togglePasswordImage.setOnClickListener {
            togglePassVisability(passEditText, togglePasswordImage)
        }
        changeColor()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show()
        }
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        inputEmail.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN)) {
                passEditText.requestFocus()
            }
            return@setOnEditorActionListener true
        }
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

    private fun changeColor() {
        val lblDontHaveAccount = findViewById<TextView>(R.id.lblDontHaveAccount)
        val spannableStringValue = SpannableString(lblDontHaveAccount.text)
        val text = lblDontHaveAccount.text
        val color = ContextCompat.getColor(this, R.color.layout_exe_button_color)
        val signUpColor = ForegroundColorSpan(color)
        val indexStart = text.indexOf("Singup")
        val indexEnd = indexStart + "Singup".length

        val clickableSingup = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(applicationContext, "Singup clicked!", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.bgColor = Color.WHITE
            }
        }
        spannableStringValue.setSpan(clickableSingup, indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringValue.setSpan(signUpColor, indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        lblDontHaveAccount.text = spannableStringValue
        lblDontHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}