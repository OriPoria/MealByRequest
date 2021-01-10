package com.example.testmeals.database

import androidx.room.PrimaryKey
import com.example.meals.database.Meal
import com.google.gson.annotations.SerializedName

data class Category(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

data class Categories(
        val categories: List<Category>
)