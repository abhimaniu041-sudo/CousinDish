package com.example.dishapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(
    private var dishes: List<Dish>,
    private val onClick: (Dish) -> Unit
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.dishName)
        val location: TextView = view.findViewById(R.id.dishLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.name.text = dish.name
        holder.location.text = if (dish.state.isNotEmpty()) "${dish.state}, ${dish.country}" else dish.country
        holder.itemView.setOnClickListener { onClick(dish) }
    }

    override fun getItemCount(): Int = dishes.size

    fun updateList(newList: List<Dish>) {
        dishes = newList
        notifyDataSetChanged()
    }
}
