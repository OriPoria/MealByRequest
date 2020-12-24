 package com.example.testmeals.ui.search

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.testmeals.R
import com.example.testmeals.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_search.*



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
         val adapter = this.context?.let {
             ArrayAdapter(it, android.R.layout.simple_spinner_item,
                     resources.getStringArray(R.array.search_options))
         }
         spinner.adapter = adapter

         spinner.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener {
             override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                myViewModel.category.value = parent?.getItemAtPosition(position).toString()
             }

             override fun onNothingSelected(parent: AdapterView<*>?) {
                 Log.i("i", "onNothingSelected")
             }

         }

     }




}