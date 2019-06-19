package com.epam.task6.models

import com.epam.task6.models.CommPrefsItemsTypes.CAR

data class Car(
    val title: String,
    val year: Int,
    val driveUnit: String,
    val numberOfDoors: Int
) : Vehicle {

    override val rowType: Int
        get() = CAR
    override val rowId: String
        get() = title
}