package com.example.pilldozer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedicineScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)


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