package com.example.dietary.retrofit.data.nutrients

import com.example.dietary.TabsFragments.SearchRecipes
import com.example.dietary.TabsFragments.SearchRecipesByIngredients

data class NutrientsResponse(
        val list:List<FindByNutrients>?=null
)