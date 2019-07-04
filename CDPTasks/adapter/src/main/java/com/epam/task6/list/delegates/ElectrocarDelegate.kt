package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.task6.R
import com.epam.task6.models.Electrocar

class ElectrocarDelegate(viewType: Int) :
    BaseDelegate<Electrocar, ElectrocarDelegate.ElectrocarViewHolder>(viewType) {
    override fun onBindViewHolder(
        items: List<Electrocar>,
        position: Int,
        holder: ElectrocarViewHolder,
        payloads: MutableList<Any>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(items: List<Electrocar>, position: Int, holder: ElectrocarViewHolder) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, items: List<Electrocar>): ElectrocarViewHolder {
        val electrocarViewHolder =
            ElectrocarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_electrocar, parent, false))
        electrocarViewHolder.itemView.setOnClickListener {
            val adapterPosition = electrocarViewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition] //TODO here is the item
            }
        }
        return electrocarViewHolder
    }

    inner class ElectrocarViewHolder(view: View) : BaseViewHolder<Electrocar>(itemView = view) {

        private var titleView: TextView? = null
        private var yearView: TextView? = null
        private var distanceView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.electrocar_title)
            yearView = itemView.findViewById(R.id.electrocar_year)
            distanceView = itemView.findViewById(R.id.electrocar_distance)
        }

        override fun onBind(model: Electrocar) {
            titleView?.text = model.title
            yearView?.text = model.year.toString()
            distanceView?.text = model.engineMileage.toString()
        }
    }
}

