package com.example.testmeals.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmeals.R
import com.example.testmeals.common.OnItemClickListener
import com.example.testmeals.database.Category
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.item_meal_result.view.*

class CategoryAdapter(private val categoriesList: List<CategoryResultItem>,
                      private val fragment: Fragment,
                      private val clickListener: OnItemClickListener<CategoryResultItem>) :
        RecyclerView.Adapter<CategoryAdapter.HeaderViewHolder>() {
    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.category_image
        val catergoryTitle: TextView = view.category_text

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val position = position % categoriesList.size
        val currentItem = categoriesList[position]
        Glide.with(fragment). load(currentItem.imageResource).into(holder.imageView)
        holder.catergoryTitle.text = currentItem.categoryName
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int = (categoriesList.size*2 + 1)

}