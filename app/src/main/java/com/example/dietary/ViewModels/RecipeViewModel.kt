package com.example.dietary.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dietary.retrofit.Repository.SearchRepository
import com.example.dietary.retrofit.Service.SpoonacularService
import com.example.dietary.retrofit.data.Recipes.Response
import kotlinx.android.synthetic.main.fragment_blank.view.*

class RecipeViewModel(application: Application) : AndroidViewModel(application)
{val repository = SearchRepository(SpoonacularService.create())

    fun getAllRecipes(query: String, maxFat: Int,number:Int): LiveData<Response?>? {
      return  repository.searchRecipes(query,maxFat,number)
    }
}
