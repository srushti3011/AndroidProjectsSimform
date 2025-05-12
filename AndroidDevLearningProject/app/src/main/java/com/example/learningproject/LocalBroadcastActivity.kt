package com.example.learningproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.learningproject.databinding.ActivityLocalBroadcastBinding

class LocalBroadcastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocalBroadcastBinding
    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val dataReceived = intent?.getStringExtra("DATA_SENT")
            Log.i("TAG", dataReceived ?: "no data")
            binding.tvReceivedText.text = dataReceived
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLocalBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(
                receiver,
                IntentFilter("com.example.learningproject.LOCAL_BROADCAST")
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}