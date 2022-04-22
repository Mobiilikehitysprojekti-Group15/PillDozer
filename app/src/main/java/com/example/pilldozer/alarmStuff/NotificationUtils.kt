package com.example.pilldozer.alarmStuff

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.pilldozer.AlertScreen.Companion.notificationMedName
import com.example.pilldozer.AlertScreen.Companion.notificationMedQuantity
import com.example.pilldozer.MainActivity
import com.example.pilldozer.MedicineScreen
import com.example.pilldozer.R

class  NotificationUtils(base: Context) : ContextWrapper(base) {

    val MYCHANNEL_ID = "App Alert Notification ID"
    val MYCHANNEL_NAME = "App Alert Notification"

    private var manager: NotificationManager? = null


    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(MYCHANNEL_ID, MYCHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    fun getManager() : NotificationManager {
        if (manager == null) manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    //Luodaan ilmoitus joka tulee näkyviin kun alarm aktivoituu
    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.pill_icon)

        return NotificationCompat.Builder(applicationContext, MYCHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lääke")
            .setLargeIcon(bitmap)
            .setContentText("Aika ottaa " + notificationMedName + ", otettava määrä " + notificationMedQuantity)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
    }
}