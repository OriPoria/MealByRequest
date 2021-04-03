package com.example.meals.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals_table")
data class MealItem(
    @PrimaryKey
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strMeal: String,
    val strMealThumb: String,
)

data class MealItems(
    val meals: List<MealItem>
)