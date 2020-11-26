package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import com.example.meals.database.Meal
import com.example.meals.database.Meals
import com.example.meals.network.ConnectivityInterceptorImpl
import com.example.meals.network.TheMealDBApiService
import com.example.meals.network.response.MealNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MealsRepositoryImpl : MealsRepository {
    override suspend fun getMealByName(name: String): LiveData<Meal> {
        return withContext(Dispatchers.IO) {
            val theMealDBApiService: TheMealDBApiService = TheMealDBApiService(
                    ConnectivityInterceptorImpl()
            )
            val mealNetworkDataSource: MealNetworkDataSource = MealNetworkDataSource(theMealDBApiService)
            mealNetworkDataSource.fetchMeal(name)
            return@withContext mealNetworkDataSource.downloadedMeal
        }
    }
}