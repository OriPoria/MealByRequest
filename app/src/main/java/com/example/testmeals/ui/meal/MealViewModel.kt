package com.example.testmeals.ui.meal

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmeals.common.NonNullLiveData
import com.example.testmeals.database.MealDetails
import com.example.testmeals.database.MealDetailsTest
import com.example.testmeals.repository.MealsRepository

class MealViewModel @ViewModelInject constructor(
    private val repository: MealsRepository
) : ViewModel() {

    val meal = repository.getValue()

    var testvalue = MutableLiveData<String>()





}