package com.example.pilldozer.medicineDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.pilldozer.MEDICINE_ID
import com.example.pilldozer.R
import androidx.activity.viewModels

class MedicineDetailActivity : AppCompatActivity() {

    private val medicineDetailViewModel by viewModels<MedicineDetailViewModel> {
        MedicineDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_detail)

        var currentMedicineId: Long? = null

        val medicineName: TextView = findViewById(R.id.tvMedicineName)
        val medicineQuantity: TextView = findViewById(R.id.tvMedicineQuantity)
        val medicineDescription : TextView = findViewById(R.id.tvMedicineDescription)
        val removeMedicineButton : Button = findViewById(R.id.buMedRemove)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentMedicineId = bundle.getLong(MEDICINE_ID)
        }

        currentMedicineId?.let {
            val currentMedicine = medicineDetailViewModel.getMedicineForId(it)
            medicineName.text = currentMedicine?.name
            medicineQuantity.text = currentMedicine?.quantity
            medicineDescription.text = currentMedicine?.description

            removeMedicineButton.setOnClickListener {
                if (currentMedicine != null) {
                    medicineDetailViewModel.removeMedicine(currentMedicine)
                }
                finish()
            }

        }

    }
}