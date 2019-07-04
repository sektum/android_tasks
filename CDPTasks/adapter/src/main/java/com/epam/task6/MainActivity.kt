package com.epam.task6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout.VERTICAL
import com.epam.task6.list.adapter.VehiclesAdapter
import com.epam.task6.models.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val carsList = listOf(
        Car("Chevrolet Camaro", 2015, "Rare drive", 2),
        Car("Ford Mustang", 2017, "Rare drive", 2),
        Electrocar("Tesla3000", 2020, 1100),
        Bicycle("Xiaomi", 2019),
        Electrocar("Tesla1", 2018, 350),
        Motorcycle("Kawasaki", 2010, "Motocross"),
        Crossover("Volkswagen Tiguan", 2014, 4.7),
        Car("Ferrari California", 1995, "Four-wheel drive", 2),
        Motorcycle("Yamaha", 2019, "Sport")
    )
    private lateinit var viewAdapter: VehiclesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = VehiclesAdapter()
        viewAdapter.updateList(carsList)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            // set the custom adapter to the RecyclerView
            adapter = viewAdapter
        }.also {
            it.addItemDecoration(DividerItemDecoration(it.context, VERTICAL))
        }
    }
}
