package expo.modules.rightqnative

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
      val serviceIntent = Intent(context, BrokerService::class.java)
      context.startForegroundService(serviceIntent)
    }
  }
}
