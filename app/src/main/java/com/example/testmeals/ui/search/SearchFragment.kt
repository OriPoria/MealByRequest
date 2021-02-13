  package com.example.testmeals.ui.search


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.testmeals.R
import com.example.testmeals.common.EndlessCycleOnScrollListener
import com.example.testmeals.common.OnItemClickListener
import com.example.testmeals.databinding.FragmentSearchBinding
import com.example.testmeals.ui.MainActivity
import com.example.testmeals.ui.list.ItemCategoryView
import com.example.testmeals.ui.list.ItemSearchView
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.delay


 /**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment(): Fragment(), OnItemClickListener<CategoryResultItem> {
     private val TAG = "SearchFragment"
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

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         val e = EndlessCycleOnScrollListener(categories_recycler_view)
         myViewModel.categoriesListResult.observe(viewLifecycleOwner, {
             progress_bar.isVisible = false
             categories_recycler_view.setHasFixedSize(true)
             categories_recycler_view.adapter = CategoryAdapter(it, this, this)
             e.liveListSize.postValue(categories_recycler_view.adapter!!.itemCount)
             categories_recycler_view.addOnScrollListener(e)
         })
     }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         //change color button to black
         button.setBackgroundColor(-16777216)
         button.setOnClickListener {
             myViewModel.createViewItem = ItemSearchView()
             //test===============
             myViewModel.setQuery(search_text.text.toString())
             //test===============
             //incorrect!!!
             myViewModel.getDataByName()
         }
         myViewModel.getCategoriesFromRepository()

     }


     private var checkIfFragmentReinitialized = 0
     override fun onStart() {
         super.onStart()
         checkIfFragmentReinitialized+=1
     }

     override fun onItemClick(item: CategoryResultItem) {
         myViewModel.createViewItem = ItemCategoryView()
         myViewModel.setQuery(item.categoryName)
         myViewModel.getDataByCategory()
     }

 }