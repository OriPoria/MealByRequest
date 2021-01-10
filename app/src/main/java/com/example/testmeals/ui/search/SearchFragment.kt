 package com.example.testmeals.ui.search


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.testmeals.R
import com.example.testmeals.common.EndlessCycleOnScrollListener
import com.example.testmeals.databinding.FragmentResultsBinding
import com.example.testmeals.databinding.FragmentSearchBinding
import com.example.testmeals.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.android.synthetic.main.fragment_search.*


 /**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment(): Fragment() {
     val TAG = "SearchFragment"
     private var _binding: FragmentSearchBinding? = null
     private val binding get() = _binding!!

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
         //change color button to black
         button.setBackgroundColor(-16777216)
         button.setOnClickListener {
             //test===============
             myViewModel.setQuery(search_text.text.toString())
             //test===============
             //incorrect!!!
             myViewModel.getDataFromRepository()
         }
         myViewModel.getCategoriesFromRepository()
         val e = EndlessCycleOnScrollListener(categories_recycler_view)
         myViewModel.categoriesListResult.observe(viewLifecycleOwner, {
            Log.i(TAG, "IN observer")
             categories_recycler_view.setHasFixedSize(true)
             categories_recycler_view.adapter = CategoryAdapter(it, this)
             categories_recycler_view.addOnScrollListener(e)
             e.liveListSize.postValue(categories_recycler_view.adapter!!.itemCount)
         })

     }

//     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//         super.onViewCreated(view, savedInstanceState)
//         val recyclerView = view.findViewById<RecyclerView>(R.id.categories_recycler_view)
//         recyclerView.addOnScrollListener(EndlessCycleOnScrollListener(recyclerView))
//     }

     override fun onStop() {
         super.onStop()
         Log.d(TAG, "onStop")
     }

}