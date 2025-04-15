package com.example.learningproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class CustomSnackBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_snack_bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnShowCustomSnackBar = findViewById<Button>(R.id.btnShowCustomSnackBar)
        btnShowCustomSnackBar.setOnClickListener { view ->
            showCustomSnackBar(view)
        }
        btnShowCustomSnackBar.requestFocus()

        val btnDefaultSnackBar = findViewById<Button>(R.id.btnDefaultSnackBar)
        btnDefaultSnackBar.setOnClickListener {
            Snackbar.make(btnDefaultSnackBar, "Snackbar shown", Snackbar.LENGTH_LONG)
                .setAction("Cancel"){
                    Log.i("Snackbar", "")
                }.show()
        }
    }

    private fun showCustomSnackBar(view: View) {
        val snackbar = Snackbar.make(view, "Hello", Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val customSnackbarView = LayoutInflater.from(view.context).
            inflate(R.layout.custom_toast, null)
        val sbar = snackbar.view as? ViewGroup
        if(sbar != null){
            sbar.removeAllViews()
            sbar.addView(customSnackbarView)
        }
        snackbar.show()
    }
}
