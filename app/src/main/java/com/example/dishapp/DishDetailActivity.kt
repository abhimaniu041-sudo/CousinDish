package com.example.dishapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class DishDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val dishId = intent.getIntExtra("dish_id", -1)
        val dish = DishRepository.loadDishes(this).find { it.id == dishId }

        val titleView = findViewById<TextView>(R.id.detailTitle)
        val locationView = findViewById<TextView>(R.id.detailLocation)
        val descView = findViewById<TextView>(R.id.detailDescription)
        val ingredientsView = findViewById<TextView>(R.id.detailIngredients)
        val stepsView = findViewById<TextView>(R.id.detailSteps)

        if (dish != null) {
            titleView.text = dish.name
            locationView.text = if (dish.state.isNotEmpty()) "${dish.state}, ${dish.country}" else dish.country
            descView.text = dish.description
            ingredientsView.text = dish.ingredients.joinToString("\n") { "• $it" }
            stepsView.text = dish.steps.mapIndexed { index, step -> "${index + 1}. $step" }.joinToString("\n\n")
        }
    }
}
