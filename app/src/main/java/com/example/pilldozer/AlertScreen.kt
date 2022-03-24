package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class AlertScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_screen)

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Lääke muistutuksen lisäys"
        actionbar.setDisplayHomeAsUpEnabled(true)





        val saveMedButton: Button = findViewById(R.id.buSaveNewMed)
        saveMedButton.setOnClickListener {

            addMedicine()
        }
        //val editTextMedName = findViewById<EditText>(R.id.giveMedName)
        //editTextMedQuantity = findViewById<EditText>(R.id.giveMedQuantity)

        //MedDataObject.medicineName = editTextMedName.text.toString()
        //MedDataObject.medicineQuantity = editTextMedQuantity.text.toString()

    }

    private fun addMedicine() {
        val resultIntent = Intent()

        val editTextMedName = findViewById<EditText>(R.id.giveMedName)
        val editTextMedQuantity = findViewById<EditText>(R.id.giveMedQuantity)

        MedDataObject.medicineName = editTextMedName.text.toString()
        MedDataObject.medicineQuantity = editTextMedQuantity.text.toString()

        if (MedDataObject.medicineName.isNullOrEmpty() || MedDataObject.medicineQuantity.isNullOrEmpty()) {
            val noMedAddedText: TextView = findViewById(R.id.medAddError)
            noMedAddedText.text = "Anna lääkkeen nimi sekä määrä"
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {

            /*val intent = Intent(this, MedicineScreen::class.java)
            intent.putExtra("medicineName", MedDataObject.medicineName)
            intent.putExtra("medicineQuantity", MedDataObject.medicineQuantity)
            startActivity(intent)*/




            resultIntent.putExtra("medicineName", MedDataObject.medicineName)
            resultIntent.putExtra("medicineQuantity", MedDataObject.medicineQuantity)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()

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