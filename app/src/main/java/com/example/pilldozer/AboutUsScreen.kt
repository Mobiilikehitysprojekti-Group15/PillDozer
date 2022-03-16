package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutUsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val actionbar = supportActionBar

        actionbar!!.title = "Tietoa meistä"

        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}