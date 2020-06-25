package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.model.Food
import com.example.myapplication.network.APIService
import com.example.shakil.kotlinjsonexample.Interface.Common


import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_food.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodActivity : AppCompatActivity() {

    lateinit var mService: APIService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: FoodAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        mService = Common.retrofitService

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
        dialog.show()

        mService.getMovieList().enqueue(object : Callback<MutableList<Food>> {
            override fun onFailure(call: Call<MutableList<Food>>, t: Throwable) {
                TODO("not implemented")
            }

            override fun onResponse(
                call: Call<MutableList<Food>>,
                response: Response<MutableList<Food>>
            ) {
                adapter = FoodAdapter(baseContext, response.body() as MutableList<Food>)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter

                dialog.dismiss()
            }

        })
    }
}
