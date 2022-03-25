package com.example.pilldozer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.R




const val MEDICINE_ID = "medicine id"

class MedicineScreen : AppCompatActivity() {
    private val newMedicineActivityRequestCode = 1
    private val itemsViewModel by viewModels<ItemsViewModel> {
        ItemsViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)


        val medicineAdapter = CustomAdapter { medicine -> adapterOnClick(medicine) }

        //tässä kohti menossa !!!!!!!!!!!!!!!!!!
        val recyclerView: RecyclerView = findViewById(R.id.medList)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.medList)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

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

        






        val newMedicineButton: Button = findViewById(R.id.buNewMed)
        newMedicineButton.setOnClickListener {
            startAlertScreen()
        }

        val actionbar = supportActionBar

        actionbar!!.title = "Lääkkeet"

        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }



    fun startAlertScreen() {
        val intent = Intent(this, AlertScreen::class.java)
        startActivity(intent)

    }
}