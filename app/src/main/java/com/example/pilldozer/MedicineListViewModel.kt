package com.example.pilldozer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pilldozer.data.DataSource
import com.example.pilldozer.data.Medicine
import java.lang.IllegalArgumentException
import kotlin.random.Random

class MedicineListViewModel(val dataSource: DataSource) : ViewModel() {

    val medicineLiveData = dataSource.getMedicineList()

    // If name, quantity and description are given, create new Medicine
    fun insertMedicine(medicineName: String?, medicineQuantity: String?, medicineDescription: String?) {
        if (medicineName == null || medicineQuantity == null || medicineDescription == null) {
            return
        }

        println("inser medicineen paasty")
        val image = dataSource.getMedicineImageAsset()
        val newMedicine = Medicine(
            Random.nextLong(),
            medicineName,
            medicineQuantity,
            image,
            medicineDescription

        )

        dataSource.addMedicine(newMedicine)
    }
}

class MedicineListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(MedicineListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicineListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
