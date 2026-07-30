package com.example.dishapp

data class Dish(
    val id: Int,
    val name: String,
    val country: String,
    val state: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>
)
