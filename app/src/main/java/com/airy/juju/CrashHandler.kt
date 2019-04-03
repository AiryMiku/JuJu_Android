package com.airy.juju

import android.os.Build
import android.R.attr.versionCode
import android.R.attr.versionName
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.os.Environment
import java.nio.file.Files.exists
import android.os.Environment.MEDIA_MOUNTED
import android.os.Process.myPid
import android.os.Process.killProcess
import android.os.Environment.getExternalStorageDirectory
import android.os.Process
import android.widget.Toast
import java.io.BufferedWriter
import java.io.IOException
import java.io.PrintWriter
import java.io.Writer


/**
 * Created by Airy on 2019/4/3
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class CrashHandler : Thread.UncaughtExceptionHandler {
    private var mDefaultCrashHandler: Thread.UncaughtExceptionHandler? = null
    private var mContext: Context? = null

    fun init(context: Context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
        mContext = context.applicationContext
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     *
     * 这个方法的回调，该方法运行在发生crash的那个线程。如果UI线程crash，就运行在UI线程
     * 如果发生在子线程，就运行在子线程；因此不可以在这个方法中弹出toast或者dialog（因为这个线程的消息循环已经被破坏了）
     * 但是可以跳转到一个Activity，在这个Acitivty中弹dialog或者toast
     */
    override fun uncaughtException(thread: Thread, ex: Throwable) {
        try {
            Toast.makeText(mContext, ex.message, Toast.LENGTH_LONG).show()
            //导出异常信息到SD卡中
//            dumpExceptionToSDCard(ex)
//            uploadExceptionToServer()
            //这里可以通过网络上传异常信息到服务器（完成下面的方法uploadExceptionToServer），便于开发人员分析日志从而解决bug
        } catch (e: IOException) {
            e.printStackTrace()
        }

        ex.printStackTrace()
        // 发生crash之后，需要将进程杀掉，因为此时程序不能继续往下运行，程序状态已不对
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler!!.uncaughtException(thread, ex)
        } else {
            Process.killProcess(Process.myPid())
        }

    }

//    @Throws(IOException::class)
//    private fun dumpExceptionToSDCard(ex: Throwable) {
//        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            if (DEBUG) {
//                Log.w(TAG, "sdcard unmounted,skip dump exception")
//                return
//            }
//        }
//
//        val dir = File(PATH)
//        if (!dir.exists()) {
//            dir.mkdirs()
//        }
//        val current = System.currentTimeMillis()
//        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(current))
//        val file = File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX)
//
//        try {
//            val pw = PrintWriter(BufferedWriter(FileWriter(file)))
//            pw.println(time)
//            dumpPhoneInfo(pw)
//            pw.println()
//            ex.printStackTrace(pw)
//            pw.close()
//        } catch (e: Exception) {
//            Log.e(TAG, "dump crash info failed")
//        }
//
//    }

//    @Throws(NameNotFoundException::class)
//    private fun dumpPhoneInfo(pw: PrintWriter) {
//        val pm = mContext!!.getPackageManager()
//        val pi = pm.getPackageInfo(mContext!!.getPackageName(), PackageManager.GET_ACTIVITIES)
//        pw.print("App Version: ")
//        pw.print(pi.versionName)
//        pw.print('_')
//        pw.println(pi.versionCode)
//
//        //android版本号
//        pw.print("OS Version: ")
//        pw.print(Build.VERSION.RELEASE)
//        pw.print("_")
//        pw.println(Build.VERSION.SDK_INT)
//
//        //手机制造商
//        pw.print("Vendor: ")
//        pw.println(Build.MANUFACTURER)
//
//        //手机型号
//        pw.print("Model: ")
//        pw.println(Build.MODEL)
//
//        //cpu架构
//        pw.print("CPU ABI: ")
//        pw.println(Build.CPU_ABI)
//    }

//    private fun uploadExceptionToServer() {
//        //TODO Upload Exception Message To Your Web Server
//    }

    companion object {
        private val TAG = "CrashHandler"
        private val DEBUG = true

        private val PATH = Environment.getExternalStorageDirectory().path + "/CrashTest/log/"
        private val FILE_NAME = "crash"
        private val FILE_NAME_SUFFIX = ".trace"

        fun getInstance(): CrashHandler  = CrashHandler()
    }

}