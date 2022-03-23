package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlertScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_screen)

        val actionbar = supportActionBar

        actionbar!!.title = "Lääke muistutuksen lisäys"

        actionbar.setDisplayHomeAsUpEnabled(true)


        val saveMedButton: Button = findViewById(R.id.buSaveNewMed)
        saveMedButton.setOnClickListener {
            //data.add(ItemsViewModel(R.drawable.ic_healing, "Item ", "1000mg"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}