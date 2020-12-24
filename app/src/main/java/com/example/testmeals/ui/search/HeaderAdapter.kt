package com.example.testmeals.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmeals.R
import kotlinx.android.synthetic.main.item_meal_result.view.*

class HeaderAdapter(private val mealResultList: List<MealResultItem>, private val fragment: Fragment): RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.image_recycler
        val mealTitle: TextView = view.meal_title
        val mealSubtitle: TextView = view.meal_subtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_result, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val currentItem = mealResultList[position]
        Glide.with(fragment).load(currentItem.imageResource).into(holder.imageView)
        holder.mealTitle.text = currentItem.name
        holder.mealSubtitle.text = currentItem.area + ", " + currentItem.category
    }

    override fun getItemCount(): Int = mealResultList.size
}