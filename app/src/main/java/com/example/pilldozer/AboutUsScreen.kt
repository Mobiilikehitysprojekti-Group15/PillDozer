package com.example.pilldozer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.aboutUs.AboutUsAdapter
import com.example.pilldozer.aboutUs.AboutUsViewModel
import com.example.pilldozer.aboutUs.AboutUsViewModelFactory
import com.example.pilldozer.aboutUs.Person


class AboutUsScreen : AppCompatActivity() {
    private val aboutUsViewModel by viewModels<AboutUsViewModel> {
        AboutUsViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val actionbar = supportActionBar
        actionbar!!.title = "Tietoa meistä"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val personAdapter = AboutUsAdapter{}

        val recyclerView = findViewById<RecyclerView>(R.id.personRecyclerView)
        recyclerView.adapter = personAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        aboutUsViewModel.personLiveData.observe(this, {
            it?.let {
                personAdapter.submitList(it as MutableList<Person>)

            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}