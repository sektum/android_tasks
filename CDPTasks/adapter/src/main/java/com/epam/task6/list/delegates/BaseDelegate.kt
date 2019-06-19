package com.epam.task6.list.delegates

import android.view.ViewGroup
import com.epam.task6.models.Vehicle

abstract class BaseDelegate<M : Vehicle, VH : BaseViewHolder<M>>(val type: Int) {

    abstract fun onCreateViewHolder(parent: ViewGroup, items: List<M>): VH

    abstract fun onBindViewHolder(items: List<M>, position: Int, holder: VH)
}