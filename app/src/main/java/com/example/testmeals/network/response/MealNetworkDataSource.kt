package com.example.meals.network.response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meals.database.Meal
import com.example.meals.network.TheMealDBApiService
import java.lang.Exception

class MealNetworkDataSource(
    private val theMealDBApiService: TheMealDBApiService
) {
    private val _downloadedMeal = MutableLiveData<Meal>()

    val downloadedMeal:LiveData<Meal>
        get() =_downloadedMeal

    suspend fun fetchMeal(meal: String) {
        try {
            val fetchedMeal = theMealDBApiService
                .getMeal(meal)
                .await()
            _downloadedMeal.postValue(fetchedMeal.meals[0])
        } catch (e: Exception) {

        }
    }
}