package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import com.example.meals.database.Meal
import com.example.meals.network.ConnectivityInterceptorImpl
import com.example.meals.network.TheMealDBApiService
import com.example.meals.network.response.MealNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val mealApi: TheMealDBApiService) : MealsRepository {
    override suspend fun getMealsByName(name: String): LiveData<List<Meal>> {
        return withContext(Dispatchers.IO) {
//            val theMealDBApiService = TheMealDBApiService(
//                    ConnectivityInterceptorImpl()
//            )
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMeals(name)
            return@withContext mealNetworkDataSource.downloadedMeal
        }
    }

    override suspend fun getMealsByCategory(name: String): LiveData<List<Meal>> {
        return withContext(Dispatchers.IO) {
//            val theMealDBApiService = TheMealDBApiService(
//                    ConnectivityInterceptorImpl()
//            )
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMeals(name)
            return@withContext mealNetworkDataSource.downloadedMeal
        }    }
}