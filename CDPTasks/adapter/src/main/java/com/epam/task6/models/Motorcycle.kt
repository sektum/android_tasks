package com.epam.task6.models

import com.epam.task6.models.CommPrefsItemsTypes.MOTORCYCLE

data class Motorcycle(
    val title: String,
    val year: Int,
    val type: String
) : Vehicle {

    override val rowType: Int
        get() = MOTORCYCLE
    override val rowId: String
        get() = title
}