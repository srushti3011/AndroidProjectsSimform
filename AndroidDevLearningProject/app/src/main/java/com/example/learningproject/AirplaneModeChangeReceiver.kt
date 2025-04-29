package com.example.learningproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled =
            intent?.getBooleanExtra("state", false) ?: return

        Toast.makeText(
            context,
            "Airplane mode $isAirplaneModeEnabled",
            Toast.LENGTH_LONG).show()
    }
}