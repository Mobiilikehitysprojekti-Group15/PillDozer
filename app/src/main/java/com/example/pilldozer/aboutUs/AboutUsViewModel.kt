package com.example.pilldozer.aboutUs

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pilldozer.MedicineListViewModel
import com.example.pilldozer.data.DataSource
import com.example.pilldozer.data.Medicine
import java.lang.IllegalArgumentException
import kotlin.random.Random

class AboutUsViewModel(val aboutUsSource: AboutUsSource) : ViewModel() {

    val personLiveData = aboutUsSource.getPersonList()

    // If name, quantity and description are given, create new Medicine
    fun insertPerson(
        personName: String?,
        personDescription: String?

        ) {
        if (personName == null || personDescription == null) {
            return
        }


        val image = aboutUsSource.getPersonImageAsset()
        val newPerson = Person(
            Random.nextLong(),
            image,
            personName,
            personDescription

        )

        aboutUsSource.addPerson(newPerson)
    }


}

class AboutUsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(AboutUsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AboutUsViewModel(
                aboutUsSource = AboutUsSource.getAboutUsSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}