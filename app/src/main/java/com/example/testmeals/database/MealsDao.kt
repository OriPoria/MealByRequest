package com.example.testmeals.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meals.database.Meal

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(meal: Meal)

    @Query("select * from meals_table where idMeal == :id")
    fun getMeal(id: Int): LiveData<Meal>
}