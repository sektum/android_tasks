package com.epam.task6.list.delegates

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(model: M)
}