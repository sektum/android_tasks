package com.epam.task2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.epam.cdptasks.R

class PageViewModel : ViewModel() {

    private val index = MutableLiveData<Int>()
    private lateinit var resources: ResourceWrapper
    val text: LiveData<String> = Transformations.map(index) {
        when (index.value) {
            1 -> resources.getString(R.string.first_page_greeting, it)
            2 -> resources.getString(R.string.second_page_greeting, it)
            else -> resources.getString(R.string.third_page_greeting, it)
        }
    }

    fun setIndex(index: Int) {
        this.index.value = index
    }

    fun setResources(resources: ResourceWrapper) {
        this.resources = resources
    }
}