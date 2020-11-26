 package com.example.testmeals.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.testmeals.R
import com.example.testmeals.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.EnumSet.of
import java.util.Optional.of


 /**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment(): Fragment() {

     private val myViewModel: MyViewModel by lazy {
         return@lazy ViewModelProvider(activity as MainActivity).get(MyViewModel::class.java)
     }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         button.setOnClickListener {
             myViewModel.getDataFromRepository()
         }
         val adapter = this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,
                 resources.getStringArray(R.array.search_options)) }
         spinner.adapter = adapter

     }







}