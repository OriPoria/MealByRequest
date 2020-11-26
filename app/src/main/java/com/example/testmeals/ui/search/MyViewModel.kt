package com.example.testmeals.ui.search

import androidx.lifecycle.*
import com.example.meals.database.Meal
import com.example.meals.database.Meals
import com.example.testmeals.repository.MealsRepository
import com.example.testmeals.repository.MealsRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repository: MealsRepository = MealsRepositoryImpl()
    var listResult = MutableLiveData<List<MealResultItem>>()
    val array = ArrayList<MealResultItem>()
    var i = 0

    fun getDataFromRepository() {
        CoroutineScope(Dispatchers.Main).launch {
            val myMealTest : LiveData<Meal> = repository.getMealByName("Arrabiata")

            myMealTest.observeForever {
                val mealItem = MealResultItem(0, it.strArea, it.strCategory)
                array.clear()
                array += mealItem
            }


            listResult.postValue(array)
            i+=3


        }



    }



}