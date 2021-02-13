package com.example.testmeals.database

import androidx.room.PrimaryKey

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