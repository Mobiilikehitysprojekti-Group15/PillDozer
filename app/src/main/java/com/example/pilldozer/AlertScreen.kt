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

const val MEDICINE_NAME =  "name"
const val MEDICINE_QUANTITY = "quantity"
const val MEDICINE_DESCRIPTION = "description"

class AlertScreen : AppCompatActivity() {
    private lateinit var addMedicineName: EditText
    private lateinit var addMedicineQuantity: EditText
    private lateinit var addMedicineDescription: EditText


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

        addMedicineName = findViewById(R.id.giveMedName)
        addMedicineQuantity = findViewById(R.id.giveMedQuantity)
        addMedicineDescription = findViewById(R.id.giveMedDescription)



        //val editTextMedName = findViewById<EditText>(R.id.giveMedName)
        //editTextMedQuantity = findViewById<EditText>(R.id.giveMedQuantity)

        //MedDataObject.medicineName = editTextMedName.text.toString()
        //MedDataObject.medicineQuantity = editTextMedQuantity.text.toString()

    }

    private fun addMedicine() {
        val resultIntent = Intent()

        if (addMedicineName.text.isNullOrEmpty() || addMedicineQuantity.text.isNullOrEmpty() || addMedicineDescription.text.isNullOrEmpty()) {
            val noMedAddedText: TextView = findViewById(R.id.medAddError)
            noMedAddedText.text = "Anna lääkkeen nimi sekä määrä"
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {

            val name = addMedicineName.text.toString()
            val quantity = addMedicineQuantity.text.toString()
            val description = addMedicineDescription.text.toString()

            println(name)
            println(quantity)
            println(description)

            resultIntent.putExtra(MEDICINE_NAME, name)
            resultIntent.putExtra(MEDICINE_QUANTITY, quantity)
            resultIntent.putExtra(MEDICINE_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)

            /*val intent = Intent(this, MedicineScreen::class.java)
            intent.putExtra("medicineName", MedDataObject.medicineName)
            intent.putExtra("medicineQuantity", MedDataObject.medicineQuantity)
            startActivity(intent)*/


        }
        finish()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}