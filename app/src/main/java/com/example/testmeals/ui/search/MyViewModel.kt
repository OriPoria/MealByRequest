package com.example.testmeals.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.meals.database.Meal
import com.example.testmeals.common.NonNullLiveData
import com.example.testmeals.repository.MealsRepository
import androidx.lifecycle.MutableLiveData

import com.example.testmeals.repository.MealsRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyViewModel @ViewModelInject constructor(
    private val repository: MealsRepository)
    : ViewModel() {
    val TAG = "MyViewModel"
    var listResult = MutableLiveData<List<MealResultItem>>()
    var category = MutableLiveData<String>()
    val array = ArrayList<MealResultItem>()
    private val categoriesArray = ArrayList<CategoryResultItem>()
    val categoriesListResult = MutableLiveData<List<CategoryResultItem>>()

    //test==========================
    private var query = NonNullLiveData("a")
    fun setQuery(str:String) {
        Log.i("i","in set query")
        query.value = str
    }
    private var answer : LiveData<String> =  Transformations.switchMap(query, {test(it)})
    fun test(ss:String):LiveData<String> {
        return MutableLiveData<String>(ss)
    }
    val mw = answer.observeForever {
        Log.i("i" , it)
    }
    //test===================================

    fun getDataFromRepository() {
        CoroutineScope(Dispatchers.Main).launch {
            val myMealTest : LiveData<List<Meal>> = repository.getMealsByName(query.value)
            array.clear()

            myMealTest.observeForever {

                for (meal in it) {
                    val mealItem = MealResultItem(meal.strMealThumb,meal.strMeal, meal.strArea, meal.strCategory)
                    array += mealItem
                }
            }
            listResult.postValue(array)
        }
    }

    fun getCategoriesFromRepository() {
        Log.d(TAG, "in get categories size: " + categoriesArray.size.toString())
        if (categoriesArray.size == 0) {
            CoroutineScope(Dispatchers.Default).launch {
                val future = CoroutineScope(Dispatchers.Default).async {
                    return@async repository.getCategories()
                }
                val result = future.await()
                if (result.value != null) {
                    result.value?.let {
                        for (cat in it) {
                            val catItem = CategoryResultItem(cat.strCategoryThumb, cat.strCategory)
                            categoriesArray += catItem
                        }
                    }
                    categoriesListResult.postValue(categoriesArray)
                }
            }
        }
    }



}