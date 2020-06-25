package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Food

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*

class FoodAdapter(private val context: Context, private val foodList: MutableList<Food>): RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(foodList[position].img_url).into(holder.image)
        holder.tvName.text = foodList[position].name
        holder.tvRestaurant.text = foodList[position].restaurant
        holder.tvCost.text = foodList[position].cost
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var tvName : TextView
        var tvRestaurant : TextView
        var tvCost : TextView

        init {
            image = itemView.image_food
            tvName = itemView.tvName
            tvRestaurant = itemView.tvRestaurant
            tvCost = itemView.tvCost
        }
    }
}