package com.airy.juju.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.airy.juju.Constant
import com.airy.juju.eventBus.NotificationEvent
import com.airy.juju.util.UserCenter
import io.crossbar.autobahn.websocket.WebSocketConnection
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler
import org.greenrobot.eventbus.EventBus

class NotificationService : Service() {

    companion object {
        const val TAG = "NotificationService"
    }
    private val mBinder: IBinder = NotificationClientBinder()
    private val mWsConnection: WebSocketConnection = WebSocketConnection()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        initWebSocket()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mWsConnection.isConnected) {
            mWsConnection.sendClose()
        }
    }

    private fun initWebSocket() {
        try {
            mWsConnection.connect(Constant.Server.WS_PUSH_URI+"${UserCenter.getUserId()}/", object :WebSocketConnectionHandler(){

                override fun onOpen() {
                    Log.d(TAG, "onOpen")
                }

                override fun onMessage(payload: String?) {
                    Log.d(TAG, "onMessage -> $payload")
                    EventBus.getDefault().post(payload?.let { NotificationEvent(it) })
                }

                override fun onClose(code: Int, reason: String?) {
                    Log.d(TAG, "onClose code->$code, reason->$reason")
                }
            })
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }


    inner class NotificationClientBinder : Binder() {

        fun getService(): NotificationService {
            return this@NotificationService
        }

        fun sendMessage(msg: String) {
            if (mWsConnection.isConnected) {
                mWsConnection.sendMessage(msg)
            } else {
                Log.d(TAG, "connection is close")
            }
        }
    }
}
