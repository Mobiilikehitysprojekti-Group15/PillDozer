package com.example.pilldozer.aboutUs

import androidx.annotation.DrawableRes

data class Person (
    val id: Long,
    @DrawableRes
    val personImage: Int?,
    val personName: String,
    val personDescription: String

    )