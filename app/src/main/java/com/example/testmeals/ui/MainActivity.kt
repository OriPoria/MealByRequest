package com.example.testmeals.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.example.testmeals.R
import com.example.testmeals.ui.meal.MealViewModel
import com.example.testmeals.ui.meal.SingleMealActivity
import com.example.testmeals.ui.search.MyViewModel
import com.example.testmeals.ui.search.ResultsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val myViewModel: MyViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        val sup = supportActionBar
        myViewModel.listResult.observe(this, {
            nav_host_fragment.childFragmentManager.beginTransaction().
            replace(R.id.nav_host_fragment,ResultsFragment()).commit()
            sup?.title = getString(R.string.results_fragment)
        })
        mealViewModel.meal.observe(this, {
            val intent = Intent(this, SingleMealActivity::class.java)
            intent.putExtra("mealDetails", it)
            startActivity(intent)
        })
    }


}