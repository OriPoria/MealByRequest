package com.example.testmeals.ui.list

import com.example.testmeals.ui.search.MealResultItem

interface ItemListCreator<T, S> {
    fun create(item: T): S
}