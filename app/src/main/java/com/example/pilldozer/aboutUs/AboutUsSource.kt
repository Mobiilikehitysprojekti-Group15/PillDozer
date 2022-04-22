package com.example.pilldozer.aboutUs

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pilldozer.R
import com.example.pilldozer.aboutUs.Person
import com.example.pilldozer.aboutUs.personList
import com.example.pilldozer.data.DataSource
import com.example.pilldozer.data.Medicine

class AboutUsSource(resources: Resources) {
    private val initialPersonList = personList(resources)
    private val personLiveData = MutableLiveData(initialPersonList)

    fun addPerson(person: Person) {
        val currentList = personLiveData.value
        if (currentList == null) {
            personLiveData.postValue(listOf(person))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, person)
            personLiveData.postValue(updatedList)
        }
    }

    fun getPersonList(): LiveData<List<Person>> {
        return personLiveData
    }

    fun getPersonImageAsset(): Int {
        return R.drawable.pill_icon
    }

    companion object {
        private var INSTANCE: AboutUsSource? = null

        fun getAboutUsSource(resources: Resources): AboutUsSource {
            return synchronized(AboutUsSource::class) {
                val newInstance = INSTANCE ?: AboutUsSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}