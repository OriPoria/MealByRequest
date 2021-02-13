package com.example.testmeals.ui.list

import com.example.meals.database.MealItem
import com.example.testmeals.ui.search.MealResultItem

class ItemSearchView: ItemListCreator<MealItem, MealResultItem> {
    override fun create(item: MealItem): MealResultItem {
        return MealResultItem(item.strMealThumb,item.strMeal, item.strArea,item.strCategory)
    }
}