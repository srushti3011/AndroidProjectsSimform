package com.example.learningproject

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityPermissionsHomeBinding

class PermissionsHomeActivity : AppCompatActivity() {
    private var specialSettingDenialCount = 0
    private val settingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        val powerManager = getSystemService(POWER_SERVICE) as android.os.PowerManager
        if (powerManager.isIgnoringBatteryOptimizations(packageName)) {
            Log.i("TAG", "Exemption permission granted by the user, app can now proceed")
        } else {
            Log.i("TAG", "Exemption permission denied by the user")
            specialSettingDenialCount++
            Log.i("TAG", specialSettingDenialCount.toString())
            if (specialSettingDenialCount >= 3) {
                AlertDialog.Builder(this)
                    .setMessage("You need to provide the permission because")
                    .setTitle("Permission required")
                    .setPositiveButton("Grant permission") { dialog, _ ->
                        dialog.cancel()
                        requestIgnoreBatteryOptimizationPermission()
                    }
                    .setNegativeButton("Don't grant permission") { dialog, _ ->
                        dialog.cancel()
                        Toast.makeText(
                            this@PermissionsHomeActivity,
                            "Can't proceed with desired action as permission not granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .create()
                    .show()
            } else {
                requestIgnoreBatteryOptimizationPermission()
            }
        }
    }
    private lateinit var binding: ActivityPermissionsHomeBinding
    private val wifiLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPermissionsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnOpenCamera.setOnClickListener {
                Log.i("TAG", "inside btnOpenCamera")
                if (ContextCompat.checkSelfPermission(
                        this@PermissionsHomeActivity, android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_DENIED) {
                    Log.i("TAG", "Inside not granted permission")
                    requestForCamera()
                } else {
                    Log.i("TAG", "Granted permission")
                }
            }

            btnSpecialSettings.setOnClickListener {
                val powerManager = getSystemService(POWER_SERVICE) as android.os.PowerManager
                val isIgnoring = powerManager.isIgnoringBatteryOptimizations(packageName)
                if (!isIgnoring) {
                    requestIgnoreBatteryOptimizationPermission()
                } else {
                    Log.i("TAG", "Already exempted from battery optimizations")
                }
            }

            btnOpenWifiSettings.setOnClickListener {
                val intent = Intent(
                    android.provider.Settings.ACTION_WIFI_SETTINGS
                )
                wifiLauncher.launch(intent)
            }
        }
    }

    @SuppressLint("BatteryLife")
    fun requestIgnoreBatteryOptimizationPermission() {
        val intent = Intent(
            android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        ).apply {
            data = Uri.parse("package:$packageName")
        }
        settingsLauncher.launch(intent)
    }

    private fun requestForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.CAMERA
        )) {
            AlertDialog.Builder(this)
                .setTitle("Camera Permission Required")
                .setMessage("This app needs camera access to take photos.")
                .setPositiveButton("Ok") { _, _ ->
                    requestCameraPermission()
                }
                .setNegativeButton("Cancel", null)
                .show()
        } else {
            Log.i("TAG", "Inside else to request permission")
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        Log.i("TAG", "Inside requestCameraPermission")
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA), 200
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}