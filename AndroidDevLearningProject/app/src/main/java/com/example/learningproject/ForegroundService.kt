package com.example.learningproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlin.concurrent.thread

class ForegroundService: Service() {
    private val TAG = "Service"

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        thread(start = true) {
            while (true) {
                Log.i(TAG, "foreground service running")
                Thread.sleep(1000)
            }
        }
        startForegroundService()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun generatePendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun generateNotificationChannel(): NotificationChannel {
        val channel = NotificationChannel("ID", "NOTI_CHANNEL", NotificationManager.IMPORTANCE_LOW)
        val notificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
        return channel
    }

    private fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(this, "ID")
            .setContentText("Foreground service notification")
            .setContentTitle("Foreground service example")
            .setContentIntent(generatePendingIntent())
            .setSmallIcon(R.drawable.carrot)
            .setOngoing(true)
            .build()
        return notification
    }

    private fun startForegroundService() {
        generateNotificationChannel()
        val notification = createNotification()
        startForeground(111, notification)
    }
}