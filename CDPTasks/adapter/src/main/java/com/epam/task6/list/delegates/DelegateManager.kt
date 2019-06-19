package com.epam.task6.list.delegates

import android.util.SparseArray
import com.epam.task6.models.Vehicle

class DelegateManager<T : Vehicle> {
    private val delegateSparseArray: SparseArray<BaseDelegate<*, *>> = SparseArray(0)

    fun getDelegate(viewType: Int): BaseDelegate<T, BaseViewHolder<T>> =
        delegateSparseArray.get(viewType) as? BaseDelegate<T, BaseViewHolder<T>>
            ?: throw RuntimeException("Delegate with type $viewType isn't registered")


    fun registerDelegate(delegate: BaseDelegate<*, *>): DelegateManager<T> {
        val type = delegate.type
        if (delegateSparseArray.get(type) == null) {
            delegateSparseArray.put(type, delegate)
        }
        return this
    }

    fun getItemViewType(position: Int, mainList: List<Vehicle>): Int =
        mainList[position].rowType
}