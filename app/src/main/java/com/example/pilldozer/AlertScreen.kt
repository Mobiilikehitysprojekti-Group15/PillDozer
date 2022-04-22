package com.example.pilldozer

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import com.example.pilldozer.alarmStuff.AlarmReceiver

const val MEDICINE_NAME =  "name"
const val MEDICINE_QUANTITY = "quantity"
const val MEDICINE_DESCRIPTION = "description"
const val MEDICINE_HOUR = "hour"
const val MEDICINE_MINUTE = "minute"

class AlertScreen : AppCompatActivity() {
    private lateinit var addMedicineName: EditText
    private lateinit var addMedicineQuantity: EditText
    private lateinit var addMedicineDescription: EditText
    lateinit var timePicker: TimePicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_screen)
        onClickTime()

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

    }



    fun onClickTime() {

        val textView = findViewById<TextView>(R.id.tvTime)
        timePicker = findViewById<TimePicker>(R.id.timePickerSpinner)
        timePicker.setIs24HourView(true)

        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour

            if (textView != null) {

                // näyttää ajan muodossa 00:00 jos kennonaika on pienempi kuin 10, eikä muodossa 0:0
                val hourStr = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute

                // näyttää kellossa olevan ajan
                val msg = "Aika: $hourStr : $min "
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


            createAlarm()

            notificationMedName = name
            notificationMedQuantity = quantity

            resultIntent.putExtra(MEDICINE_NAME, name)
            resultIntent.putExtra(MEDICINE_QUANTITY, quantity)
            resultIntent.putExtra(MEDICINE_DESCRIPTION, description)
            resultIntent.putExtra(MEDICINE_HOUR, hour)
            resultIntent.putExtra(MEDICINE_MINUTE, minute)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()

        }
    }

    //Luodaan ajastettu hälytys
    private fun createAlarm() {

        val calendar: Calendar = Calendar.getInstance()
        if (Build.VERSION.SDK_INT >= 23) {
            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                timePicker.hour,
                timePicker.minute,
                0
            )
        } else {
            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                timePicker.currentHour,
                timePicker.currentMinute,
                0
            )
        }
        setAlarm(calendar.timeInMillis)
    }

    private fun setAlarm(timeInMillis: Long) {

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver.AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, (0..2147483647).random(), intent, 0)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }


    companion object {
        var notificationMedName = ""
        var notificationMedQuantity = ""
        var hourTemp = 0
        var minuteTemp = 0
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}

