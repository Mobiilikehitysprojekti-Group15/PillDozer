package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.pilldozer.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val newFeedBackActivityRequestCode = 1
    private val newLoginScreenActivityRequestCode = 2

    lateinit var thankYouTextView: TextView
    lateinit var thumbUpImage: ImageView
    lateinit var greetingTextView: TextView

    companion object {
        var loginId: Int? = 0 // Käyttäjän id

        var loginCheck: Boolean? = false

        var overAllRating = 0F
        var newRating = 0F
        var timesRated = 0
        var mathVar = 0F

        var userName = ""


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



        /*
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
        */

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
        startActivityForResult(intent, newFeedBackActivityRequestCode)

    }

    fun startLogin() {
        val intent = Intent(this, LoginScreen::class.java)
        startActivityForResult(intent, newLoginScreenActivityRequestCode)

    }

    fun startMedicine() {
        val intent = Intent(this, MedicineScreen::class.java)
        startActivity(intent)

    }

    fun showThanksMessage() {
        thankYouTextView = findViewById(R.id.tv_thank_feedback)
        thankYouTextView.text = "Kiitos Palautteesta!"

        thumbUpImage = findViewById(R.id.thumb_Image)
        thumbUpImage.setImageResource(R.drawable.ic_baseline_thumb_up_24)

        object : CountDownTimer(5000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                thankYouTextView.text = ""
                thumbUpImage.setImageResource(android.R.color.transparent)
            }
        }.start()
    }

    fun countOverAllRating() { // Laskee annettujen arvioiden keskiarvon
        timesRated++
        mathVar = mathVar + newRating
        overAllRating = mathVar / timesRated

    }

    fun greeting() {
        val rightNow = Calendar.getInstance()
        val currentHourIn24Format: Int =rightNow.get(Calendar.HOUR_OF_DAY)

        greetingTextView = findViewById(R.id.tv_userGreeting)


        if (currentHourIn24Format in 0..5) {
            greetingTextView.text = ("Hyvää Yötä " + userName)
        }
        else if (currentHourIn24Format in 6..11) {
            greetingTextView.text = ("Hyvää Huomenta " + userName)
        }
        else if (currentHourIn24Format in 12..18) {
            greetingTextView.text = ("Hyvää Päivää " + userName)
        }
        else if (currentHourIn24Format in 19..23) {
            greetingTextView.text = ("Hyvää Iltaa " + userName)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if(requestCode == newFeedBackActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->

                val feedBackComment = data.getStringExtra(FEEDBACK_COMMENT)
                val feedBackStars = data.getFloatExtra(FEEDBACK_STARS, 0F)

                newRating = 0F
                newRating = feedBackStars

                countOverAllRating()

                showThanksMessage()


                //println(feedBackComment)
                //println(feedBackStars)

            }
        }

        if(requestCode == newLoginScreenActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->

                userName = data.getStringExtra(LOGIN_NAME).toString()

                greeting()

            }
        }
    }
}

