package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker

const val MEDICINE_NAME =  "name"
const val MEDICINE_QUANTITY = "quantity"
const val MEDICINE_DESCRIPTION = "description"
const val MEDICINE_HOUR = "hour"
const val MEDICINE_MINUTE = "minute"

class AlertScreen : AppCompatActivity() {
    private lateinit var addMedicineName: EditText
    private lateinit var addMedicineQuantity: EditText
    private lateinit var addMedicineDescription: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_screen)
        onClickTime()

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



    private fun onClickTime() {

        val textView = findViewById<TextView>(R.id.tvTime)
        val timePicker = findViewById<TimePicker>(R.id.timePickerSpinner)
        timePicker.setIs24HourView(true)

        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour

            if (textView != null) {


                // this would make the time format like 00:00 if the time is smaller than 10 rather than 0:0
                // it's nicer looking but breaks the code because then its string rather than int
                val hourStr = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute

                // display format of time
                val msg = "Time is: $hourStr : $min "
                textView.text = msg
                textView.visibility = ViewGroup.VISIBLE

                hourTemp = hour
                minuteTemp = minute
                println(hourTemp)
                println(minuteTemp)

            }
        }
    }

    private fun addMedicine() {
        val resultIntent = Intent()

        if (addMedicineName.text.isNullOrEmpty() || addMedicineQuantity.text.isNullOrEmpty() || addMedicineDescription.text.isNullOrEmpty()) {
            val noMedAddedText: TextView = findViewById(R.id.medAddError)
            noMedAddedText.text = "Anna lääkkeen nimi, määrä sekä kuvaus"
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {

            val name = addMedicineName.text.toString()
            val quantity = addMedicineQuantity.text.toString()
            val description = addMedicineDescription.text.toString()
            val hour = hourTemp
            val minute = minuteTemp

            println(name)
            println(quantity)
            println(description)
            println(hourTemp)
            println(minuteTemp)

            resultIntent.putExtra(MEDICINE_NAME, name)
            resultIntent.putExtra(MEDICINE_QUANTITY, quantity)
            resultIntent.putExtra(MEDICINE_DESCRIPTION, description)
            resultIntent.putExtra(MEDICINE_HOUR, hour)
            resultIntent.putExtra(MEDICINE_MINUTE, minute)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()

            /*val intent = Intent(this, MedicineScreen::class.java)
            intent.putExtra("medicineName", MedDataObject.medicineName)
            intent.putExtra("medicineQuantity", MedDataObject.medicineQuantity)
            startActivity(intent)*/


        }


    }


    companion object {
        var hourTemp = 0
        var minuteTemp = 0
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}

