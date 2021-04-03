package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meals.database.MealItem
import com.example.testmeals.database.Category
import com.example.testmeals.database.MealDetails

interface MealsRepository {
    suspend fun getMealsByName(name: String) : LiveData<List<MealItem>>

    suspend fun getMealsByCategory(name: String) : LiveData<List<MealItem>>

    suspend fun getCategories() : LiveData<List<Category>>

    fun setValue(string: String)

    fun getValue() : LiveData<MealDetails>

}