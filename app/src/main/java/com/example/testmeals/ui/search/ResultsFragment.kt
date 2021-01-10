package com.example.testmeals.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testmeals.R
import com.example.testmeals.databinding.FragmentResultsBinding
import com.example.testmeals.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_results.*


class ResultsFragment : Fragment() {
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
            recycler_view.adapter = HeaderAdapter(myViewModel.array, this@ResultsFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "in ResultsFragment onDestroy")

        _binding = null
    }


}