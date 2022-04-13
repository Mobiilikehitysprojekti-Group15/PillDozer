package com.example.pilldozer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pilldozer.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel1" //notificationId = 1

    companion object {
        var loginId: Int? = 0 // Käyttäjän id

        var loginCheck: Boolean? = false
    }



    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (loginId == 0) { //jos käyttäjällä ei ole id:tä login ruutu käynnistyy
            loginCheck = false
        }
        else {
            loginCheck = true
        }

        if (loginCheck == false) {
            setContentView(binding.root)
            startLogin()
        }
        else {
            setContentView(binding.root)
        }


        val timeTextView: TextView = findViewById(R.id.countTime)

        val startCountButton: Button = findViewById(R.id.BuStartCount)
        startCountButton.setOnClickListener {
            object : CountDownTimer(30000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    timeTextView.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    timeTextView.setText("ota lääkkeet!")
                }
            }.start()

        }

        val notifButton: Button = findViewById(R.id.ScheduleMedi)
        notifButton.setOnClickListener {
            mediScheduler()
        }

        val medicineListButton: Button = findViewById(R.id.buMedicine)
        medicineListButton.setOnClickListener {
            startMedicine()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.getItemId()

        if (id == R.id.settings) {
            startSettings()
            return true
        }

        if (id == R.id.history) {
            startHistory()
            return true
        }

        if (id == R.id.aboutUs) {
            startAboutUs()
            return true
        }

        if (id == R.id.feedBack) {
            startFeedback()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /*private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.0){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply{
                description=descriptionText
            }
            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }*/

    private fun startSettings() {
        val intent = Intent(this, SettingsScreen::class.java)
        startActivity(intent)
    }

    private fun startHistory() {
        val intent = Intent(this, HistoryScreen::class.java)
        startActivity(intent)
    }

    private fun startAboutUs() {
        val intent = Intent(this, AboutUsScreen::class.java)
        startActivity(intent)
    }

    private fun startFeedback() {
        val intent = Intent(this, FeedBackScreen::class.java)
        startActivity(intent)
    }

    private fun mediScheduler() {
        val intent = Intent(this,NotifActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.pill_icon)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Lääke")
            .setLargeIcon(bitmap)
            .setContentText("Ota ne lääkkeet")
            .setContentIntent(pendingIntent)

            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)){
            notify(1,builder.build())
        }
    }

    fun startLogin() {
        val intent = Intent(this, LoginScreen::class.java)
        startActivity(intent)

    }

    fun startMedicine() {
        val intent = Intent(this, MedicineScreen::class.java)
        startActivity(intent)

    }

}

/*
object : CountDownTimer(30000, 1000) {

      override fun onTick(millisUntilFinished: Long) {
          mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
      }

      override fun onFinish() {
          mTextField.setText("done!")
      }
  }.start()
 */