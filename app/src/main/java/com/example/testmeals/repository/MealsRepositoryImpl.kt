package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import com.example.meals.database.Meal
import com.example.meals.network.ConnectivityInterceptorImpl
import com.example.meals.network.TheMealDBApiService
import com.example.meals.network.response.MealNetworkDataSource
import com.example.testmeals.database.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val mealApi: TheMealDBApiService) : MealsRepository {
    override suspend fun getMealsByName(name: String): LiveData<List<Meal>> {
        return withContext(Dispatchers.IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMeals(name)
            return@withContext mealNetworkDataSource.downloadedMeal
        }
    }

    override suspend fun getMealsByCategory(name: String): LiveData<List<Meal>> {
        return withContext(Dispatchers.IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMeals(name)
            return@withContext mealNetworkDataSource.downloadedMeal
        }
    }

    override suspend fun getCategories(): LiveData<List<Category>> {
        return withContext(IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchCategories()
            return@withContext mealNetworkDataSource.downloadedCategories
        }
    }
}