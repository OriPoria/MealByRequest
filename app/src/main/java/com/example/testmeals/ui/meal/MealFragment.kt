package com.example.testmeals.ui.meal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testmeals.R
import com.example.testmeals.ui.MainActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.testmeals.databinding.ActivityMainBinding
import com.example.testmeals.databinding.FragmentMealBinding
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : Fragment() {
    private val TAG = "MealFragment"
    private val mealViewModel: MealViewModel by lazy {
        return@lazy ViewModelProvider(activity as MainActivity).get(MealViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, mealViewModel.meal.value)
        mealViewModel.testvalue.postValue("from meal fragment test")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FragmentMealBinding>(view)?.apply {
            this.setLifecycleOwner(this@MealFragment)
            this.viewmodel = mealViewModel
        }

    }

}