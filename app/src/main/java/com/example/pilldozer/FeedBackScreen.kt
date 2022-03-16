package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FeedBackScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)

        val actionbar = supportActionBar

        actionbar!!.title = "Palaute"

        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}