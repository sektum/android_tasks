package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.task6.R
import com.epam.task6.models.Motorcycle

class MotorcycleDelegate(viewType: Int) : BaseDelegate<Motorcycle, MotorcycleDelegate.MotocycleViewHolder>(viewType) {
    override fun onBindViewHolder(
        items: List<Motorcycle>,
        position: Int,
        holder: MotocycleViewHolder,
        payloads: MutableList<Any>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(items: List<Motorcycle>, position: Int, holder: MotocycleViewHolder) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, items: List<Motorcycle>): MotocycleViewHolder {
        val motocycleViewHolder =
            MotocycleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_motorcycle, parent, false))
        motocycleViewHolder.itemView.setOnClickListener {
            val adapterPosition = motocycleViewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition] //TODO here is the item
            }
        }
        return motocycleViewHolder
    }

    inner class MotocycleViewHolder(view: View) : BaseViewHolder<Motorcycle>(itemView = view) {

        private var titleView: TextView? = null
        private var yearView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.motorcycle_title)
            yearView = itemView.findViewById(R.id.motorcycle_year)
        }

        override fun onBind(model: Motorcycle) {
            val title = model.title.plus(" ").plus(model.type)
            titleView?.text = title
            yearView?.text = model.year.toString()
        }
    }
}

