package com.example.pilldozer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.pilldozer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var loginCheck = false

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if (loginCheck == false) {
            setContentView(binding.root)
            startLogin()
        }
        else {
            setContentView(binding.root)
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
}