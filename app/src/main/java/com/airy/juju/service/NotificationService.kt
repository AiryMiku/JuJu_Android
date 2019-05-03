package com.airy.juju.service

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.airy.juju.Constant
import com.airy.juju.R
import com.airy.juju.eventBus.NotificationEvent
import com.airy.juju.util.UserCenter
import com.alibaba.fastjson.JSON
import io.crossbar.autobahn.websocket.WebSocketConnection
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import kotlin.concurrent.thread

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
//                    EventBus.getDefault().post(payload?.let { NotificationEvent(it) })
                    payload?.let {
                        makeNotification(it)
                    }
                }

                override fun onClose(code: Int, reason: String?) {
//                       initWebSocket()
//                        reconnectWs()
                    mWsConnection.reconnect()
                    Log.d(TAG, "onClose code->$code, reason->$reason")
                }
            })
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }

    private fun reconnectWs() {
        var times = 0
        while (!mWsConnection.isConnected) {
            runBlocking {
                delay(2000)
                mWsConnection.reconnect()
                ++times
                Log.d(TAG, "reconnect $times time(s)")
            }
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

    @SuppressLint("ServiceCast")
    private fun makeNotification(msg: String) {
        val jsonObejct = JSON.parseObject(msg)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val ntf = NotificationCompat.Builder(applicationContext, "Main Notif")
            .setContentTitle("收到新通知")
            .setContentText(jsonObejct.getString("event"))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
            .setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        manager.notify(1, ntf)
    }
}
