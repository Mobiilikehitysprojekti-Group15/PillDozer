package com.example.pilldozer.medicineDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pilldozer.data.DataSource
import com.example.pilldozer.data.Medicine

class MedicineDetailViewModel(private val datasource: DataSource) : ViewModel() {
    fun getMedicineForId(id: Long) : Medicine? {
        return datasource.getMedicineForId(id)
    }

    fun removeMedicine(medicine: Medicine) {
        datasource.removeMedicine(medicine)
    }
 }

class MedicineDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicineDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicineDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}