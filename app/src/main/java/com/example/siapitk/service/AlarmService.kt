package com.example.siapitk.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.JobIntentService
import java.util.*

class AlarmService : Service() {

    companion object {

        private const val TAG = "MyJobIntentService"

        /**
         * Unique job ID for this service.
         */
        private const val JOB_ID = 2
//        fun enqueueWork(context: Context?, intent: Intent?) {
//            if (intent != null) {
//                if (context != null) {
////                    , AlarmService::class.java, JOB_ID
//                    enqueueWork(context, intent)
//                }
//            }
//        }

    }


    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    override fun onCreate() {
        super.onCreate()
        setupTimer()
        Log.d("SERVICE", "Job Stared")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

//    override fun onHandleWork(intent: Intent) {
//
//    }


    private fun setupTimer() {
        timer = Timer()

        // menjadwalkan alarm per satu jam sekali
        setupTimerTask()
        Log.d("SERVICE", "Timer Set")
        timer?.schedule(timerTask, 0, 5000)
    }

    private fun setupTimerTask() {
        timerTask = object : TimerTask() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun run() {
                setScheduleNotification()
            }
        }
    }

    private fun stopTimer() {
        if (timer != null) {
            timer?.cancel()
            timer = null
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun setScheduleNotification() {
        // membuat objek calendar dan inisialisasi parameter waktunya
        val calendar = Calendar.getInstance()
        val hour = 19
        val minute = 30
        val second = 0

        // lakukan konfigurasi berdasarkan waktu yang sudah ditetapkan sebelumnya
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, second)
        }

        // membuat objek intent yang mana akan menjadi target selanjutnya
        // bisa untuk berpindah halaman dengan dan tanpa data
        val intent = Intent(applicationContext, AlarmBroadcastReceiver::class.java)
        intent.putExtra("validationTime", "19:30:00")

        // membuat objek PendingIntent yang berguna sebagai penampung intent dan aksi yang akan dikerjakan
        val requestCode = 0
        val pendingIntent =
            PendingIntent.getBroadcast(applicationContext, requestCode, intent, 0)

        // membuat objek AlarmManager untuk melakukan pendaftaran alarm yang akan dijadwalkan
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // kita buat alarm yang dapat berfungsi tepat waktu dan juga walaupun dalam kondisi HP idle
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            pendingIntent
        )

        Log.d("SERVICE", "Pending Intent Fired")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopTimer()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}


