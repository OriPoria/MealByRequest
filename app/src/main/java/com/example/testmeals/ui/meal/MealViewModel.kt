package com.example.testmeals.ui.meal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmeals.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    repository: MealsRepository
) : ViewModel() {
    private val TAG = "MealViewModel"
    val meal = repository.getValue()
}