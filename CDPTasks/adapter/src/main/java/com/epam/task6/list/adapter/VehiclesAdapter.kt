package com.epam.task6.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.epam.task6.list.delegates.*
import com.epam.task6.models.CommPrefsItemsTypes.BICYCLE
import com.epam.task6.models.CommPrefsItemsTypes.CAR
import com.epam.task6.models.CommPrefsItemsTypes.CROSSOVER
import com.epam.task6.models.CommPrefsItemsTypes.ELECTROCAR
import com.epam.task6.models.CommPrefsItemsTypes.MOTORCYCLE
import com.epam.task6.models.Vehicle

class VehiclesAdapter(private val list: List<Vehicle>) : RecyclerView.Adapter<BaseViewHolder<Vehicle>>() {
    val delegateManager = DelegateManager<Vehicle>()

    init {
        delegateManager.registerDelegate(CarDelegate(CAR))
            .registerDelegate(ElectrocarDelegate(ELECTROCAR))
            .registerDelegate(BicycleDelegate(BICYCLE))
            .registerDelegate(MotorcycleDelegate(MOTORCYCLE))
            .registerDelegate(CrossoverDelegate(CROSSOVER))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Vehicle> {
        return delegateManager.getDelegate(viewType).onCreateViewHolder(parent, list)
    }

    override fun getItemCount() =
        list.size


    override fun onBindViewHolder(viewHolder: BaseViewHolder<Vehicle>, position: Int) {
        delegateManager.getDelegate(getItemViewType(position)).onBindViewHolder(list, position, viewHolder)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(position, list)
    }
}