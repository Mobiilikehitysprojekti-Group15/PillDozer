package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pilldozer.MainActivity.Companion.loginCheck

const val LOGIN_NAME =  "user_name"


class LoginScreen : AppCompatActivity() {
    lateinit var giveName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        giveName = findViewById(R.id.et_userName)

        val buttonLogin: Button = findViewById(R.id.loginBu)
        buttonLogin.setOnClickListener {

            loginCheck = true
            sendUserName()

        }
    }

    //palauttaa annetun käyttäjänimen
    private fun sendUserName() {
        val resultIntent = Intent()
        val givenName = giveName.text.toString()

        resultIntent.putExtra(LOGIN_NAME, givenName)
        setResult(Activity.RESULT_OK, resultIntent)

        finish()
    }
}