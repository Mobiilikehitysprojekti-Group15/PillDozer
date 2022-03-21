package com.example.pilldozer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pilldozer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



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
        startActivity(intent)
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