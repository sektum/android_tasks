package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.task6.R
import com.epam.task6.models.Car

class CarDelegate(viewType: Int) : BaseDelegate<Car, CarDelegate.CarViewHolder>(viewType) {
    override fun onBindViewHolder(items: List<Car>, position: Int, holder: CarViewHolder) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, items: List<Car>): CarViewHolder {
        val carViewHolder = CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false))
        carViewHolder.itemView.setOnClickListener {
            val adapterPosition = carViewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition] //TODO here is the item
            }
        }
        return carViewHolder
    }

    inner class CarViewHolder(view: View) : BaseViewHolder<Car>(itemView = view) {

        private var titleView: TextView? = null
        private var yearView: TextView? = null
        private var carDriveUnitView: TextView? = null
        private var carNumberOfDoorsView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.car_title)
            yearView = itemView.findViewById(R.id.car_year)
            carDriveUnitView = itemView.findViewById(R.id.car_drive_unit)
            carNumberOfDoorsView = itemView.findViewById(R.id.car_number_of_doors)
        }

        override fun onBind(model: Car) {
            titleView?.text = model.title
            carDriveUnitView?.text = model.driveUnit
            yearView?.text = model.year.toString()
            carNumberOfDoorsView?.text = model.numberOfDoors.toString()
        }
    }
}

