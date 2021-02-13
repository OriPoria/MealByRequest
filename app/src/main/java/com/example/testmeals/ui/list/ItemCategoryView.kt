package com.example.testmeals.ui.list

import com.example.meals.database.MealItem
import com.example.testmeals.ui.search.MealResultItem

class ItemCategoryView: ItemListCreator<MealItem, MealResultItem> {
    override fun create(item: MealItem): MealResultItem {
        return MealResultItem(item.strMealThumb,item.strMeal, "Category", "Area")
    }
}