package com.example.testmeals.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meals.database.MealItem
import com.example.meals.network.TheMealDBApiService
import com.example.meals.network.response.MealNetworkDataSource
import com.example.testmeals.database.Category
import com.example.testmeals.database.MealDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val mealApi: TheMealDBApiService) : MealsRepository {
    override suspend fun getMealsByName(name: String): LiveData<List<MealItem>> {
        return withContext(IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMeals(name)
            return@withContext mealNetworkDataSource.downloadedMealResult
        }
    }

    override suspend fun getMealsByCategory(category: String): LiveData<List<MealItem>> {
        return withContext(IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchMealsByCategory(category)
            return@withContext mealNetworkDataSource.downloadedMealResult
        }
    }

    override suspend fun getCategories(): LiveData<List<Category>> {
        return withContext(IO) {
            val mealNetworkDataSource = MealNetworkDataSource(mealApi)
            mealNetworkDataSource.fetchCategories()
            return@withContext mealNetworkDataSource.downloadedCategories
        }
    }
    val myFullMeal = MutableLiveData<MealDetails>()
    override fun setValue(string: String) {
        CoroutineScope(IO).launch {
            val mnds =  MealNetworkDataSource(mealApi)
            val waitOnSusFunc = async { mnds.fetchfullMeals(string) }
            waitOnSusFunc.await()
            myFullMeal.postValue(mnds.downloadedChosenMeal.value?.get(0))
        }
    }

    override fun getValue(): LiveData<MealDetails> {
        return myFullMeal
    }
}