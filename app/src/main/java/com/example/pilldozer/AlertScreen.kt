package com.example.pilldozer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlertScreen : MedicineScreen() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_screen)

        val actionbar = supportActionBar

        actionbar!!.title = "Lääke muistutuksen lisäys"

        actionbar.setDisplayHomeAsUpEnabled(true)





        val saveMedButton: Button = findViewById(R.id.buSaveNewMed)
        saveMedButton.setOnClickListener {
            val editTextMedName = findViewById<EditText>(R.id.giveMedName)
            val editTextMedQuantity = findViewById<EditText>(R.id.giveMedQuantity)

            MedDataObject.medicineName = editTextMedName.text.toString()
            MedDataObject.medicineQuantity = editTextMedQuantity.text.toString()

            val intent = Intent(this, MedicineScreen::class.java)
            intent.putExtra("medicineName",MedDataObject.medicineName)
            intent.putExtra("medicineQuantity",MedDataObject.medicineQuantity)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    object MedDataObject {
        var medicineName = ""
        var medicineQuantity = ""

    }
}