package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pilldozer.MainActivity.Companion.loginCheck


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin: Button = findViewById(R.id.loginBu)
        buttonLogin.setOnClickListener {


            loginCheck = true
            onBackPressed()

        }
        val buttonLanguage: Button = findViewById(R.id.lang_button)
        buttonLanguage.setOnClickListener {

            
        }
    }
}