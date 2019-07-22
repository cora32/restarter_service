package io.humanteq.restartertest

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        func()
    }
}

fun Context.func() {
    val ishintent = Intent(this, NotificationListener::class.java).apply {
        putExtra("test", "test")
    }
    val pintent = PendingIntent.getService(this, 0, ishintent, 0)
    val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarm.cancel(pintent)
    alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pintent)
    ContextCompat.startForegroundService(this, ishintent)
}

