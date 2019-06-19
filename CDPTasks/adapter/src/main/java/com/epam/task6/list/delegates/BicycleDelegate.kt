package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.task6.R
import com.epam.task6.models.Bicycle

class BicycleDelegate(viewType: Int) : BaseDelegate<Bicycle, BicycleDelegate.BicycleViewHolder>(viewType) {
    override fun onBindViewHolder(items: List<Bicycle>, position: Int, holder: BicycleViewHolder) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, items: List<Bicycle>): BicycleViewHolder {
        val bicycleViewHolder =
            BicycleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bicycle, parent, false))
        bicycleViewHolder.itemView.setOnClickListener {
            val adapterPosition = bicycleViewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition] //TODO here is the item
            }
        }
        return bicycleViewHolder
    }

    inner class BicycleViewHolder(view: View) : BaseViewHolder<Bicycle>(itemView = view) {

        private var titleView: TextView? = null
        private var yearView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.bicycle_title)
            yearView = itemView.findViewById(R.id.bicycle_year)
        }

        override fun onBind(model: Bicycle) {
            titleView?.text = model.title
            yearView?.text = model.year.toString()
        }
    }
}

