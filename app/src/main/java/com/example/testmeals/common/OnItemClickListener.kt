package com.example.testmeals.common

import com.example.testmeals.ui.search.MealResultItem

interface OnItemClickListener<T> {
    fun onItemClick(item: T)

}