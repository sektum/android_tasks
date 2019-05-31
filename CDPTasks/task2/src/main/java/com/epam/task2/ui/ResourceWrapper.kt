package com.epam.task2.ui

import androidx.annotation.StringRes

interface ResourceWrapper{
    fun getString(@StringRes id: Int):String

    fun getString(@StringRes id: Int, vararg params: Any): String
}