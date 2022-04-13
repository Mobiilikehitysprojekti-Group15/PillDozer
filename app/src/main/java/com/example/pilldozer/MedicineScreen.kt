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


        // this creates a vertical layout Manager
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

    private fun adapterOnClick(medicine: Medicine) {
        val intent = Intent(this, MedicineDetailActivity()::class.java)
        intent.putExtra(MEDICINE_ID, medicine.id)
        startActivity(intent)
    }

    private fun addMedButtonClick() {
        val intent = Intent(this, AlertScreen::class.java)
        startActivityForResult(intent, newMedicineActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if(requestCode == newMedicineActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val medicineName = data.getStringExtra(MEDICINE_NAME)
                val medicineQuantity = data.getStringExtra(MEDICINE_QUANTITY)
                val medicineDescription = data.getStringExtra(MEDICINE_DESCRIPTION)
                //val medicineHour = data.getIntExtra(MEDICINE_HOUR)
                //val medicineMinute = data.getIntExtra(MEDICINE_MINUTE)


                medicineListViewModel.insertMedicine(medicineName, medicineQuantity, medicineDescription)

            }
        }
    }

    /*
        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        //for (i in 1..20) {
          //  data.add(ItemsViewModel(R.drawable.ic_healing, "Item " + i, "1000mg"))
        //}

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val givenMedName = intent.getStringExtra("medicineName")
        val givenMedQuantity = intent.getStringExtra("medicineQuantity")

        if (givenMedName != "" && givenMedQuantity != "") {
            data.add(ItemsViewModel(R.drawable.ic_healing, AlertScreen.MedDataObject.medicineName, AlertScreen.MedDataObject.medicineQuantity))
        }


        fun newItemViewModel() {
            data.add(ItemsViewModel(R.drawable.ic_healing, AlertScreen.MedDataObject.medicineName, AlertScreen.MedDataObject.medicineQuantity))
        }

        fun startAlertScreen() {
        val intent = Intent(this, AlertScreen::class.java)
        startActivity(intent)

    }

        */

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}

