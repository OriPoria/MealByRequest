package com.example.testmeals.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.testmeals.R
import com.example.testmeals.database.MealDetails
import kotlinx.android.synthetic.main.activity_single_meal.*
import java.lang.Exception

class SingleMealActivity() : AppCompatActivity() {
    private val TAG = "MealFragment"
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var mealDetails: MealDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_meal)
        toggle = ActionBarDrawerToggle(this, drawer_laoyout, R.string.open, R.string.close)
        drawer_laoyout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        try {
            mealDetails = intent.getSerializableExtra("mealDetails") as MealDetails
            tv.text = mealDetails.strArea
            supportActionBar?.title = mealDetails.strMeal
        } catch (e : Exception) {
            Log.i(TAG,  e.message.toString() )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}