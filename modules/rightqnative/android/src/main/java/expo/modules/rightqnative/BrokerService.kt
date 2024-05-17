package expo.modules.rightqnative

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BrokerService : Service() {
  override fun onCreate() {
    super.onCreate()
    RightQNativeModule.startBroker()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    RightQNativeModule.startBroker()
    return START_STICKY
  }

  override fun onBind(intent: Intent?): IBinder? {
    return null
  }
}
