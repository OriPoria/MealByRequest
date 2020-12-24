package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import com.example.meals.database.Meal

interface MealsRepository {
    suspend fun getMealsByName(name: String) : LiveData<List<Meal>>

    suspend fun getMealsByCategory(name: String) : LiveData<List<Meal>>

}