package com.example.meals.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meals.database.MealItem
import com.example.meals.network.TheMealDBApiService
import com.example.testmeals.database.Category
import java.lang.Exception

class MealNetworkDataSource(
    private val theMealDBApiService: TheMealDBApiService
) {
    private val TAG = "MealNetworkDataSource"
    private val _downloadedMeal = MutableLiveData<List<MealItem>>()
    private val _downloadedCategories = MutableLiveData<List<Category>>()

    val downloadedCategories: LiveData<List<Category>>
        get() = _downloadedCategories

    val downloadedMealResult:LiveData<List<MealItem>>
        get() =_downloadedMeal

    suspend fun fetchMeals(meal: String) {
        try {
            val fetchedMeals = theMealDBApiService
                .getMeals(meal)
                .await()

            _downloadedMeal.postValue(fetchedMeals.meals)
        } catch (e: Exception) {
            Log.i("exception","fetch meals exception")
        }
    }
    suspend fun fetchMealsByCategory(c: String) {
        try {
            val fetchedMeals = theMealDBApiService
                    .getMealsFilteredByCategory(c)
                    .await()
            _downloadedMeal.postValue(fetchedMeals.meals)
        } catch (e: Exception) {
            Log.i("exception","fetch meals exception")
        }
    }
    suspend fun fetchCategories() {
        try {
            _downloadedCategories.postValue(theMealDBApiService.getCategories().await().categories)
        } catch (e: Exception) {
            Log.i("exception","fetch categories exception")
        }
    }
}