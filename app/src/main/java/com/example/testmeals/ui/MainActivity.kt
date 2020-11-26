package com.example.testmeals.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import com.example.meals.network.ConnectivityInterceptorImpl
import com.example.meals.network.TheMealDBApiService
import com.example.meals.network.response.MealNetworkDataSource
import com.example.testmeals.R
import com.example.testmeals.ui.history.HistoryFragment
import com.example.testmeals.ui.search.MyViewModel
import com.example.testmeals.ui.search.ResultsFragment
import com.example.testmeals.ui.search.SearchFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val myViewModel: MyViewModel by viewModels()
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


        myViewModel.listResult.observe(this, Observer {
            nav_host_fragment.childFragmentManager.beginTransaction().
            replace(R.id.nav_host_fragment,ResultsFragment()).commit()
        })





    }


}