package com.example.learningproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityServicesHomeBinding
import android.Manifest
import android.content.IntentFilter
import androidx.annotation.RequiresApi

class ServicesHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServicesHomeBinding
    private lateinit var airplaneReceiver: AirplaneModeChangeReceiver

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityServicesHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backIntent = Intent(this, BackgroundService::class.java)
        val foreIntent = Intent(this, ForegroundService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )
        }

        binding.btnBackgroundService.setOnClickListener {
            startService(backIntent)
        }

        binding.btnStopBackgroundService.setOnClickListener {
            stopService(backIntent)
        }

        binding.btnStartForegroundService.setOnClickListener {
            startForegroundService(foreIntent)
        }

        binding.btnStopForegroundService.setOnClickListener {
            stopService(foreIntent)
        }

        airplaneReceiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airplaneReceiver, it, RECEIVER_NOT_EXPORTED)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneReceiver)
    }
}