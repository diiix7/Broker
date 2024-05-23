package com.alck.RightQBroker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.util.Log

class BootReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    Log.d("BootReceiver", "Boot completed received in Boot receiver")
    if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
      val serviceIntent = Intent(context, BrokerService::class.java)
      context.startForegroundService(serviceIntent)
    }
  }
}