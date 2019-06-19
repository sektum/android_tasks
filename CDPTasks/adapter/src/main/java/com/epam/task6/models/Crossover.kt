package com.epam.task6.models

import com.epam.task6.models.CommPrefsItemsTypes.CROSSOVER

data class Crossover(
    val title: String,
    val year: Int,
    val clearance: Double
) : Vehicle {

    override val rowType: Int
        get() = CROSSOVER
    override val rowId: String
        get() = title
}