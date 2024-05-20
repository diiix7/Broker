package com.alck.RightQBroker

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import mqtt.broker.Broker
import mqtt.broker.interfaces.PacketInterceptor
import mqtt.packets.MQTTPacket
import mqtt.packets.mqtt.MQTTConnect
import mqtt.packets.mqtt.MQTTPublish
import android.os.StrictMode
import java.net.ServerSocket
import java.net.BindException

import android.util.Log

class RightQNativeModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("RightQNative")
    Events("onChange")
    Function("hello") {
      "Hello world! 👋"
    }
    AsyncFunction("setValueAsync") { value: String ->
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }
    Function("startBroker") {
      startBroker()
    }
  }

  companion object {
    fun startBroker(): Boolean {
      val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
      StrictMode.setThreadPolicy(policy)
      return try {
        Thread {
          if (isPortAvailable(1883)) {
            val broker = Broker(
              host = "0.0.0.0",
              port = 1883,
              webSocketPort = 8083,
              packetInterceptor = object : PacketInterceptor {
                override fun packetReceived(clientId: String, username: String?, password: UByteArray?, packet: MQTTPacket) {
                  when (packet) {
                    is MQTTConnect -> println(packet.clientID)
                    is MQTTPublish -> println(packet.topicName)
                  }
                }
              }
            )
            broker.listen()
          }
        }.start()
        true
      } catch (e: Exception) {
        print(e)
        false
      }
    }

    private fun isPortAvailable(port: Int): Boolean {
      return try {
        ServerSocket(port).use { }
        Log.d("BrokerService", "Port $port is available")
        true
      } catch (e: BindException) {
        Log.e("BrokerService", "Port $port is not available", e)
        false
      }
    }
  }
}
