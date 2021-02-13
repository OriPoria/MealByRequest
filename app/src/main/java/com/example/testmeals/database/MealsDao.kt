package com.example.testmeals.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meals.database.MealItem

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(mealResult: MealItem)

    @Query("select * from meals_table where idMeal == :id")
    fun getMeal(id: Int): LiveData<MealItem>
}