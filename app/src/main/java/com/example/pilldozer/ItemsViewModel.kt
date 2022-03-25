package com.example.pilldozer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pilldozer.data.DataSource
import com.example.pilldozer.data.Medicine
import java.lang.IllegalArgumentException
import kotlin.random.Random

class ItemsViewModel(val dataSource: DataSource) : ViewModel() {

    val medicineLiveData = dataSource.getMedicineList()

    // If name, quantity and description are given, create new Medicine
    fun insertMedicine(medicineName: String?, medicineQuantity: String?, medicineDescription: String?) {
        if (medicineName == null || medicineQuantity == null || medicineDescription == null) {
            return
        }

        val image = R.drawable.ic_healing
        val newMedicine = Medicine(
            Random.nextLong(),
            medicineName,
            medicineQuantity,
            medicineDescription,
            image
        )

        dataSource.addMedicine(newMedicine)
    }
}

class ItemsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemsViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
