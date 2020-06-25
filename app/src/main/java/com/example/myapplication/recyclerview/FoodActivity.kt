package com.example.myapplication.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        food_listview = findViewById<View>(R.id.food) as RecyclerView
        food_listview.layoutManager = LinearLayoutManager(this)

        adapter = FoodAdapter(this@FoodActivity, foodList)
        food_listview.adapter = adapter// set adapter

        prepareFoodData()
    }


    companion object {
        lateinit var food_listview: RecyclerView
        var foodList = ArrayList<Food_Model>()
        private var adapter: FoodAdapter? = null

    }


    private fun prepareFoodData() {
        foodList.add(Food_Model("1", "Pizza"))
        foodList.add(Food_Model("2", "Momo"))
        foodList.add(Food_Model("3", "Chaowmin"))
        foodList.add(Food_Model("4", "Samosa"))
        foodList.add(Food_Model("5", "Aalu Stick"))
        foodList.add(Food_Model("6", "Burger"))

        adapter!!.notifyDataSetChanged()
    }
}