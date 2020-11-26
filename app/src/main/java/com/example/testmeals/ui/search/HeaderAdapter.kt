package com.example.testmeals.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testmeals.R
import kotlinx.android.synthetic.main.item_meal_result.view.*

class HeaderAdapter(private val mealResultList: List<MealResultItem>): RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.image_recycler
        val str1: TextView = view.meal_title
        val str2: TextView = view.meal_subtitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_result, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val currentItem = mealResultList[position]
        //TODO: handle the image
 //       holder.imageView.setImageResource(currentItem.imageResource)
        holder.str1.text = currentItem.str1
        holder.str2.text = currentItem.str2
    }

    override fun getItemCount(): Int = mealResultList.size
}