package com.example.pilldozer.data

import androidx.annotation.DrawableRes

data class Medicine(
    val id: Long,
    val name: String,
    val quantity: String,
    @DrawableRes
    val image: Int?,
    val description: String
)

