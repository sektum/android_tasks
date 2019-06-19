package com.epam.task6.models

import com.epam.task6.models.CommPrefsItemsTypes.BICYCLE

data class Bicycle(
    val title: String,
    val year: Int
) : Vehicle {

    override val rowType: Int
        get() = BICYCLE
    override val rowId: String
        get() = title
}