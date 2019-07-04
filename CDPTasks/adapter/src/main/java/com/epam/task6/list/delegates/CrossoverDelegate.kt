package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.task6.R
import com.epam.task6.models.Crossover

class CrossoverDelegate(viewType: Int) : BaseDelegate<Crossover, CrossoverDelegate.CrossoverViewHolder>(viewType) {
    override fun onBindViewHolder(
        items: List<Crossover>,
        position: Int,
        holder: CrossoverViewHolder,
        payloads: MutableList<Any>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(items: List<Crossover>, position: Int, holder: CrossoverViewHolder) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, items: List<Crossover>): CrossoverViewHolder {
        val crossoverViewHolder =
            CrossoverViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_crossover, parent, false))
        crossoverViewHolder.itemView.setOnClickListener {
            val adapterPosition = crossoverViewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                items[adapterPosition] //TODO here is the item
            }
        }
        return crossoverViewHolder
    }

    inner class CrossoverViewHolder(view: View) : BaseViewHolder<Crossover>(itemView = view) {

        private var titleView: TextView? = null
        private var yearView: TextView? = null
        private var clearanceView: TextView? = null

        init {
            titleView = itemView.findViewById(R.id.crossover_title)
            yearView = itemView.findViewById(R.id.crossover_year)
            clearanceView = itemView.findViewById(R.id.crossover_clearance)
        }

        override fun onBind(model: Crossover) {
            titleView?.text = model.title
            yearView?.text = model.year.toString()
            clearanceView?.text = model.clearance.toString()
        }
    }
}

