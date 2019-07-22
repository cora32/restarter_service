package io.humanteq.restartertest

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.UserHandle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class NotificationListener : NotificationListenerService() {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        sendBroadcast(Intent("IWillStartAuto"))
        Log.e("Testing", "onTaskRemoved")
        bc()
        func()
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        sendBroadcast(Intent("IWillStartAuto"))
        Log.e("Testing", "onDestroy")
        bc()
        func()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(
            1123, createNotification(
                applicationContext
            ).build()
        )
        Log.e("Testing", "onStartCommand")
        return Service.START_STICKY
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e("Testing", "rebind")
    }

    private fun bc() {

        sendBroadcast(Intent("UID_REQUEST").apply {
            setPackage(packageName)
            addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        })
    }

    private fun createNotification(
        applicationContext: Context
    ): NotificationCompat.Builder {

        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }

        return NotificationCompat.Builder(applicationContext.applicationContext, channelId)
            .setOngoing(true)
            .setContentTitle("title")
            .setContentText("text")
    }

    override fun bindService(service: Intent?, conn: ServiceConnection, flags: Int): Boolean {
        Log.e("Testing", "bindService")
        return super.bindService(service, conn, flags)
    }

    override fun onNotificationChannelGroupModified(
        pkg: String?,
        user: UserHandle?,
        group: NotificationChannelGroup?,
        modificationType: Int
    ) {
        super.onNotificationChannelGroupModified(pkg, user, group, modificationType)
        Log.e("Testing", "onNotificationChannelGroupModified")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationRemoved(sbn, rankingMap)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?, rankingMap: RankingMap?, reason: Int) {
        super.onNotificationRemoved(sbn, rankingMap, reason)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onNotificationRankingUpdate(rankingMap: RankingMap?) {
        super.onNotificationRankingUpdate(rankingMap)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onInterruptionFilterChanged(interruptionFilter: Int) {
        super.onInterruptionFilterChanged(interruptionFilter)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onListenerHintsChanged(hints: Int) {
        super.onListenerHintsChanged(hints)
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.e("Testing", "onNotificationChannelGroupModified")
        applicationContext.func()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("Testing", "onBind")
        bc()
        return super.onBind(intent)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.e("Testing", "onNotificationPosted")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        Log.e("Testing", "onNotificationPosted")
    }

    override fun onNotificationChannelModified(
        pkg: String?,
        user: UserHandle?,
        channel: NotificationChannel?,
        modificationType: Int
    ) {
        super.onNotificationChannelModified(pkg, user, channel, modificationType)
        Log.e("Testing", "onNotificationChannelModified")
        applicationContext.func()
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e("Testing", "onListenerDisconnected")
        applicationContext.func()
    }
}