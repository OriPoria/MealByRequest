package com.example.testmeals.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.meals.database.MealItem
import com.example.testmeals.common.NonNullLiveData
import com.example.testmeals.repository.MealsRepository
import androidx.lifecycle.MutableLiveData
import com.example.testmeals.database.MealDetailsTest

import com.example.testmeals.ui.list.ItemListCreator
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MyViewModel @ViewModelInject constructor(
    private val repository: MealsRepository)
    : ViewModel() {
    private val TAG = "MyViewModel"

    var listResult = MutableLiveData<List<MealResultItem>>()
    private val array = ArrayList<MealResultItem>()
    val categoriesListResult = MutableLiveData<List<CategoryResultItem>>()
    private val categoriesArray = ArrayList<CategoryResultItem>()
    var category = MutableLiveData<String>()

    lateinit var createViewItem: ItemListCreator<MealItem, MealResultItem>


    //test==========================
    private var query = NonNullLiveData("a")
    fun setQuery(str:String) {
        query.value = str
    }
    private var answer : LiveData<String> =  Transformations.switchMap(query, {test(it)})
    fun test(ss:String):LiveData<String> {
        return MutableLiveData(ss)
    }
    val mw = answer.observeForever {
        Log.i(TAG , it)
    }
    //test===================================

    fun getDataByName() = CoroutineScope(Main).launch {
            getDataFromRepository(repository.getMealsByName(query.value))
        }
    fun getDataByCategory() = CoroutineScope(Main).launch {
        getDataFromRepository(repository.getMealsByCategory(query.value))
    }



    private fun getDataFromRepository(myMealResultTest: LiveData<List<MealItem>>) {
        array.clear()
        myMealResultTest.observeForever {
            for (meal in it) {
                val mealItem = createViewItem.create(meal)
                array += mealItem
            }
        }
        listResult.postValue(array)
    }

    // pipe to set the chosen meal clicked
    fun setValue(string: String) {
        repository.setValue(string)
    }

    fun getCategoriesFromRepository() {
        if (categoriesArray.size == 0) {
            CoroutineScope(Dispatchers.Main).launch {
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
                    //to test the progressbar
                    delay(5000)
                    categoriesListResult.postValue(categoriesArray)
                }
            }
        }
    }



}