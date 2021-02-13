package com.example.testmeals.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.example.testmeals.R
import com.example.testmeals.ui.meal.MealFragment
import com.example.testmeals.ui.meal.MealViewModel
import com.example.testmeals.ui.search.MyViewModel
import com.example.testmeals.ui.search.ResultsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val myViewModel: MyViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)


        myViewModel.listResult.observe(this, {
            nav_host_fragment.childFragmentManager.beginTransaction().
            replace(R.id.nav_host_fragment,ResultsFragment()).commit()
        })
        mealViewModel.meal.observe(this, {
            nav_host_fragment.childFragmentManager.beginTransaction().
            replace(R.id.nav_host_fragment, MealFragment()).commit()
        })


    }




}