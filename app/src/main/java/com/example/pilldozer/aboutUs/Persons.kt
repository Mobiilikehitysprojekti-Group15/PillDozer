package com.example.pilldozer.aboutUs

import android.content.res.Resources
import com.example.pilldozer.R
import com.example.pilldozer.aboutUs.Person

//Lista sovelluksen tekijöistä
fun personList(resources: Resources): List<Person> {
    return listOf(
        Person(
            id = 1,
            personName = resources.getString(R.string.person1_personName),
            personDescription = resources.getString(R.string.person1_personDescription),
            personImage = R.drawable.joppev1

            ),
        Person(
            id = 2,
            personName = resources.getString(R.string.person2_personName),
            personDescription = resources.getString(R.string.person2_personDescription),
            personImage = R.drawable.nannav1

            ),
        Person(
            id = 3,
            personName = resources.getString(R.string.person3_personName),
            personDescription = resources.getString(R.string.person3_personDescription),
            personImage = R.drawable.joosepv1

            ),
        Person(
            id = 4,
            personName = resources.getString(R.string.person4_personName),
            personDescription = resources.getString(R.string.person4_personDescription),
            personImage = R.drawable.jaakkov1

        )
    )
}