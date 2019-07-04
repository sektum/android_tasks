package com.epam.task6.list.diff

import android.support.v7.util.DiffUtil
import com.epam.task6.models.Vehicle

open class BaseVehicleDiffUtil : DiffUtil.Callback() {

    protected lateinit var oldList: List<Vehicle>
    protected lateinit var newList: List<Vehicle>

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    fun setData(oldList: List<Vehicle>, newList: List<Vehicle>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldList[oldItemPosition].rowType == newList[newItemPosition].rowType &&
                oldList[oldItemPosition].rowId == newList[newItemPosition].rowId)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldList[oldItemPosition] == newList[newItemPosition])
}