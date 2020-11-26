package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import com.example.meals.database.Meal
import com.example.meals.database.Meals

interface MealsRepository {
    suspend fun getMealByName(name: String) : LiveData<Meal>
}