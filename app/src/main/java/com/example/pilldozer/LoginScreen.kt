package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin: Button = findViewById(R.id.loginBu)
        buttonLogin.setOnClickListener {
            onBackPressed()


        }
    }
}