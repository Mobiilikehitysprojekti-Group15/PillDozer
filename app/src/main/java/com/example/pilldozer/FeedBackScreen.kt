package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

const val FEEDBACK_COMMENT =  "comment"
const val FEEDBACK_STARS = "star_rating"

class FeedBackScreen : AppCompatActivity() {

    private lateinit var sendButton: Button
    private lateinit var commentText: EditText
    private lateinit var ratingStars: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        onClickFeedBack()

        val actionbar = supportActionBar
        actionbar!!.title = "Palaute"
        actionbar.setDisplayHomeAsUpEnabled(true)



        sendButton.setOnClickListener {
            sendFeedback()
        }
    }

    private fun onClickFeedBack() {
        sendButton = findViewById(R.id.buSendFeedback)
        commentText = findViewById(R.id.et_feedback_comment)
        ratingStars = findViewById(R.id.feedback_stars)
    }

    private fun sendFeedback() {
        val resultIntent = Intent()

        val givenComment = commentText.text.toString()
        val givenStars = ratingStars.rating

        println(givenStars)
        println(givenComment)

        resultIntent.putExtra(FEEDBACK_STARS, givenStars)
        resultIntent.putExtra(FEEDBACK_COMMENT, givenComment)

        setResult(Activity.RESULT_OK, resultIntent)

        finish()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}