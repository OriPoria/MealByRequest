package com.example.testmeals.ui.search

import android.util.Log
import androidx.lifecycle.*
import com.example.meals.database.Meal
import com.example.testmeals.repository.MealsRepository
import com.example.testmeals.repository.MealsRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    
    private val repository: MealsRepository = MealsRepositoryImpl()
    var listResult = MutableLiveData<List<MealResultItem>>()
    var category = MutableLiveData<String>()
    val array = ArrayList<MealResultItem>()
    var i = 0

    fun getDataFromRepository() {
        CoroutineScope(Dispatchers.Main).launch {
            val name = "A"
            val myMealTest : LiveData<List<Meal>> = repository.getMealsByName(name)
            array.clear()

            myMealTest.observeForever {

                for (meal in it) {
                    Log.i("i", meal.strMeal)
                    val mealItem = MealResultItem(meal.strMealThumb,meal.strMeal, meal.strArea, meal.strCategory)
                    array += mealItem
                }

            }
            listResult.postValue(array)
            i+=3


        }



    }



}