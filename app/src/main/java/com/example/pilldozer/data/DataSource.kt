package com.example.pilldozer.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialMedicineList = medicineList(resources)
    private val medicineLiveData = MutableLiveData(initialMedicineList)

    //Adding medicine
    fun addMedicine(medicine: Medicine) {
        val currentList = medicineLiveData.value
        if (currentList == null) {
            medicineLiveData.postValue(listOf(medicine))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, medicine)
            medicineLiveData.postValue(updatedList)
        }
    }

    //Removin medicine
    fun removeMedicine(medicine: Medicine) {
        val currentList = medicineLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(medicine)
            medicineLiveData.postValue(updatedList)
        }
    }

    //Return medicine given an ID
    fun getMedicineForId(id: Long): Medicine? {
        medicineLiveData.value?.let { medicines ->
            return medicines.firstOrNull(){ it.id == id}
        }
        return null
    }

    fun getMedicineList(): LiveData<List<Medicine>> {
        return medicineLiveData
    }


    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}
