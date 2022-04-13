package com.example.pilldozer.alarmStuff

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri

import androidx.test.core.app.ApplicationProvider.getApplicationContext


class AlarmReceiver {
    class AlarmReceiver : BroadcastReceiver() {



        override fun onReceive(context: Context, intent: Intent) {

            // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
            val notificationUtils = NotificationUtils(context)
            val notification = notificationUtils.getNotificationBuilder().build()
            notificationUtils.getManager().notify(150, notification)


        }
    }
}