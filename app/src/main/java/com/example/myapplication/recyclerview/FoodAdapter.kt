package com.example.myapplication.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.list_row.view.*

class FoodAdapter(context: Context, private val Food: ArrayList<Food_Model>
) : RecyclerView.Adapter<FoodAdapter.ViewHodler>() {

    private val mContext: Context

    init {
        mContext = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FoodAdapter.ViewHodler {
        val food_row = LayoutInflater.from(mContext).inflate(R.layout.list_row,parent,false)
        return ViewHodler(food_row)
    }

    override fun getItemCount(): Int {
        return Food.size
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHodler, position: Int) {

        val model = Food[position]
        holder.foodName!!.text = model.foodName

    }

    inner class ViewHodler(v : View) : RecyclerView.ViewHolder(v) {

        val foodName = v.foodName

    }
}