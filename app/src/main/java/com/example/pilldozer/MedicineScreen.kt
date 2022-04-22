package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.data.Medicine
import com.example.pilldozer.medicineDetail.MedicineDetailActivity

const val MEDICINE_ID = "medicine id"

class MedicineScreen : AppCompatActivity() {
    private val newMedicineActivityRequestCode = 1
    private val medicineListViewModel by viewModels<MedicineListViewModel> {
        MedicineListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        val headerAdapter = HeaderAdapter()
        val medicineAdapter = CustomAdapter { medicine -> adapterOnClick(medicine) }
        val concatAdapter = ConcatAdapter(headerAdapter, medicineAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.medList)
        recyclerView.adapter = concatAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        medicineListViewModel.medicineLiveData.observe(this, {
            it?.let {
                medicineAdapter.submitList(it as MutableList<Medicine>)
                headerAdapter.updateMedicineCount(it.size)
            }
        })

        val newMedicineButton: View = findViewById(R.id.buNewMed)
        newMedicineButton.setOnClickListener {
            addMedButtonClick()
        }

        val actionbar = supportActionBar
        actionbar!!.title = "Lääkkeet"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    // Lääkettä painaessa aukaistaan kyseisen lääkkeen oma sivu
    private fun adapterOnClick(medicine: Medicine) {
        val intent = Intent(this, MedicineDetailActivity()::class.java)
        intent.putExtra(MEDICINE_ID, medicine.id)
        startActivity(intent)
    }

    private fun addMedButtonClick() {
        val intent = Intent(this, AlertScreen::class.java)
        startActivityForResult(intent, newMedicineActivityRequestCode)
    }

    // Saadaan lääkkeen tiedot ja lisätään lääke listaan
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if(requestCode == newMedicineActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val medicineName = data.getStringExtra(MEDICINE_NAME)
                val medicineQuantity = data.getStringExtra(MEDICINE_QUANTITY)
                val medicineDescription = data.getStringExtra(MEDICINE_DESCRIPTION)

                medicineListViewModel.insertMedicine(medicineName, medicineQuantity, medicineDescription)

            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}

