package com.example.dishapp

import android.content.Context
import org.json.JSONArray

object DishRepository {
    fun loadDishes(context: Context): List<Dish> {
        val jsonStr = context.assets.open("dishes.json").bufferedReader().use { it.readText() }
        val arr = JSONArray(jsonStr)
        val list = mutableListOf<Dish>()
        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)
            val ingredientsArr = obj.getJSONArray("ingredients")
            val ingredients = mutableListOf<String>()
            for (j in 0 until ingredientsArr.length()) ingredients.add(ingredientsArr.getString(j))
            val stepsArr = obj.getJSONArray("steps")
            val steps = mutableListOf<String>()
            for (j in 0 until stepsArr.length()) steps.add(stepsArr.getString(j))
            list.add(
                Dish(
                    id = obj.getInt("id"),
                    name = obj.getString("name"),
                    country = obj.getString("country"),
                    state = obj.optString("state", ""),
                    description = obj.getString("description"),
                    ingredients = ingredients,
                    steps = steps
                )
            )
        }
        return list
    }
}
