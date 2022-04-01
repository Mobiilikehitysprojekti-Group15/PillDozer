package com.example.pilldozer.data

import android.content.res.Resources
import com.example.pilldozer.R



fun medicineList(resources: Resources): List<Medicine> {
    return listOf(
        Medicine(
            id = 1,
            name = resources.getString(R.string.medicine1_name),
            quantity = resources.getString(R.string.medicine1_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine1_description)
        ),
        Medicine(
            id = 2,
            name = resources.getString(R.string.medicine2_name),
            quantity = resources.getString(R.string.medicine2_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine2_description)
        ),
        Medicine(
            id = 3,
            name = resources.getString(R.string.medicine3_name),
            quantity = resources.getString(R.string.medicine3_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine3_description)
        ),
        Medicine(
            id = 4,
            name = resources.getString(R.string.medicine4_name),
            quantity = resources.getString(R.string.medicine4_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine4_description)
        ),
        Medicine(
            id = 5,
            name = resources.getString(R.string.medicine5_name),
            quantity = resources.getString(R.string.medicine5_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine5_description)
        ),
        Medicine(
            id = 6,
            name = resources.getString(R.string.medicine6_name),
            quantity = resources.getString(R.string.medicine6_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine6_description)
        ),
        Medicine(
            id = 7,
            name = resources.getString(R.string.medicine7_name),
            quantity = resources.getString(R.string.medicine7_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine7_description)
        ),
        Medicine(
            id = 8,
            name = resources.getString(R.string.medicine8_name),
            quantity = resources.getString(R.string.medicine8_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine8_description)
        ),
        Medicine(
            id = 9,
            name = resources.getString(R.string.medicine9_name),
            quantity = resources.getString(R.string.medicine9_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine9_description)
        ),
        Medicine(
            id = 10,
            name = resources.getString(R.string.medicine10_name),
            quantity = resources.getString(R.string.medicine10_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine10_description)
        ),
        Medicine(
            id = 11,
            name = resources.getString(R.string.medicine11_name),
            quantity = resources.getString(R.string.medicine11_quantity),
            image = R.drawable.pill_icon,
            description = resources.getString(R.string.medicine11_description)
        )
    )
}