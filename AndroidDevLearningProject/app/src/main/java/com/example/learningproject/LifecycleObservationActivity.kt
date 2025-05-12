package com.example.learningproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Lifecycle
import com.example.learningproject.databinding.ActivityLifecycleObservationBinding

class LifecycleObservationActivity : AppCompatActivity() {

    var text = ""
    val tag = "observe"
    private lateinit var binding: ActivityLifecycleObservationBinding
    var counter = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.run {
            putInt("count", counter)
        }
        Log.i(tag, "LifecycleObservationActivity onSaveInstanceState")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLifecycleObservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i(tag, "LifecycleObservationActivity onCreate")
        val fragment = LifecycleFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        binding.textView.text = text
        binding.tvShowCounter.text = counter.toString()
        val dataReceived = intent.extras
        lifecycle.addObserver(ObserverClass())
        binding.tvDataReceived.text = dataReceived?.getString("value")
        binding.editText.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text = binding.editText.text.toString()
                binding.textView.text = text
            }
        })

        binding.btnAdd.setOnClickListener {
            counter++
            binding.tvShowCounter.text = counter.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "LifecycleObservationActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "LifecycleObservationActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "LifecycleObservationActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "LifecycleObservationActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "LifecycleObservationActivity onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(tag, "LifecycleObservationActivity onRestart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val counterStored = savedInstanceState?.getInt("count")
        counter = counterStored ?: 0
        binding.tvShowCounter.text = counterStored.toString()
        Log.i(tag, "LifecycleObservationActivity onRestoreInstanceState")
    }
}