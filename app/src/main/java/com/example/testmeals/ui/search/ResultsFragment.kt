package com.example.testmeals.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testmeals.R
import com.example.testmeals.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_results.*


class ResultsFragment : Fragment() {
    private val myViewModel: MyViewModel by lazy {
        return@lazy ViewModelProvider(activity as MainActivity).get(MyViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view.adapter = HeaderAdapter(myViewModel.array)

    }



}