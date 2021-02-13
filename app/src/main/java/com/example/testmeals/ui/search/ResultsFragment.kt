package com.example.testmeals.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testmeals.R
import com.example.testmeals.common.OnItemClickListener
import com.example.testmeals.database.MealDetailsTest
import com.example.testmeals.databinding.FragmentResultsBinding
import com.example.testmeals.ui.MainActivity
import com.example.testmeals.ui.meal.MealFragment
import com.example.testmeals.ui.meal.MealViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_results.*


class ResultsFragment : Fragment(), OnItemClickListener<MealResultItem> {
    val TAG = "ResultsFragment"
    private val myViewModel: MyViewModel by lazy {
        return@lazy ViewModelProvider(activity as MainActivity).get(MyViewModel::class.java)
    }

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResultsBinding.bind(view)

        binding.apply {
            recycler_view.setHasFixedSize(true)
            val list = myViewModel.listResult.value
            if (list != null)
                recycler_view.adapter = HeaderAdapter(list, this@ResultsFragment, this@ResultsFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "on DESTROY")
        _binding = null
    }

    override fun onItemClick(item: MealResultItem) {
        Log.i(TAG, item.name)
        myViewModel.setValue(item.name)
    }


}