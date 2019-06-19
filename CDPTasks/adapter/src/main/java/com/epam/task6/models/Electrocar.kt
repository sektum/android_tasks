package com.epam.task6.models

import com.epam.task6.models.CommPrefsItemsTypes.ELECTROCAR

data class Electrocar(
    val title: String,
    val year: Int,
    val engineMileage: Int
) : Vehicle {

    override val rowType: Int
        get() = ELECTROCAR
    override val rowId: String
        get() = title
}