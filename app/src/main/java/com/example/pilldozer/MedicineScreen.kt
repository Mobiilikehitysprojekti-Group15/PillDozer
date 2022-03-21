package com.example.pilldozer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MedicineScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        val newMedicineButton: Button = findViewById(R.id.buNewMed)
        newMedicineButton.setOnClickListener {
            startAlertScreen()
        }
    }

    fun startAlertScreen() {
        val intent = Intent(this, AlertScreen::class.java)
        startActivity(intent)

    }
}