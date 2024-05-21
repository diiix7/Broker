package com.alck.RightQBroker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log

class BrokerService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("BrokerService", "Service onCreate")
        startForegroundService()
        RightQNativeModule.startBroker()
    }

    private fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BrokerServiceChannel",
                "Broker Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

            val notification: Notification = Notification.Builder(this, "BrokerServiceChannel")
                .setContentTitle("Broker Service")
                .setContentText("The broker service is running")
                .setSmallIcon(R.drawable.ic_settings)  
                .build()

            startForeground(1, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("BrokerService", "Service onStartCommand")
        RightQNativeModule.startBroker()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
