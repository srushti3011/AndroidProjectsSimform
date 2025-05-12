package com.example.learningproject

import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityWidgetsOneBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WidgetsOneActivity : AppCompatActivity() {

    val selectedItemsHobbies: MutableList<String> = mutableListOf()
    var textViewSelectedHobbies: TextView? = null
    private lateinit var binding: ActivityWidgetsOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWidgetsOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addEventToCheckBox(binding.chkBoxTravelling)
        addEventToCheckBox(binding.chkBoxReading)
        addEventToCheckBox(binding.chkBoxSinging)

        addEventToToggleButton(binding.btnBluetoothToggle)

        val btnSwitch = findViewById<SwitchCompat>(R.id.switchButton)
        btnSwitch.setOnClickListener {
            if (btnSwitch.isChecked) {
                showToast("SwitchCompat on")
            } else {
                showToast("SwitchCompat off")
            }
        }
        setEventRadioGroup(binding.radioGroupLevelOfStudy)

        val floatButtonCall = findViewById<FloatingActionButton>(R.id.floatButtonCall)
        floatButtonCall.setOnClickListener{
            showToast("Calling...")
        }
    }
    fun showToast(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }

    fun updateSelectedHobbiesTextView() {
        val str = selectedItemsHobbies.joinToString(" ")
        textViewSelectedHobbies?.text = str
    }

    fun addEventToCheckBox(checkBox: CheckBox) {
        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                showToast("${checkBox.text} is selected")
                selectedItemsHobbies.add(checkBox.text.toString())
                updateSelectedHobbiesTextView()
            } else {
                showToast("${checkBox.text} is deselected")
                selectedItemsHobbies.remove(checkBox.text.toString())
                updateSelectedHobbiesTextView()
            }
        }
    }

    fun addEventToToggleButton(toggleButton: ToggleButton) {
        toggleButton.setOnClickListener {
            if (toggleButton.isChecked) {
                showToast("Turned on")
            } else {
                showToast("Turned off")
            }
        }
    }

    fun setEventRadioGroup(radioGroup: RadioGroup) {
        radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId) {
                R.id.radioBtnDiploma -> {
                    showToast("Diploma Checked")
                }
                R.id.radioBtnGraduate -> {
                    showToast("Graduate Checked")
                }
                R.id.radioBtnPostGraduate -> {
                    showToast("Post Graduate Checked")
                }
            }
        }
    }
}