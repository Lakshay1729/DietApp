package com.example.dietary.retrofit.Repository

import androidx.lifecycle.LiveData
import com.example.dietary.retrofit.Service.SpoonacularService
import com.example.dietary.retrofit.data.Recipes.Response
import com.example.dietary.retrofit.data.nutrients.NutrientsResponse

//import com.example.dietary.retrofit.data.Recipes
import io.reactivex.Observable

class SearchRepository(val apiService: SpoonacularService) {

    fun searchRecipes(query: String, maxFat: Int,number:Int): LiveData<Response?>? {
        return apiService.search(
                query = query,
                number= number,
                maxFat=maxFat
        )
    }
    fun SearchByNutrients(minCarbs:Int,maxCarbs:Int,minProtein:Int,maxProtein:Int,minCalories:Int,maxCalories:Int,minFat:Int,maxFat:Int,number:Int):LiveData<NutrientsResponse?>?{
        return apiService.findByNutrients(
                minCarbs =    minCarbs,
                maxCarbs =    maxCarbs,
                minProtein =  minProtein,
                maxProtein =  maxProtein,
                minCalories = minCalories,
                maxCalories = maxCalories,
                minFat =      minFat,
                maxfat =      maxFat,
                number =      number
        )
    }

//    object SearchRepositoryProvider {
//               val apiService: SpoonacularService =SpoonacularService.create()
//        fun provideSearchRepository(): SearchRepository {
//            return SearchRepository(apiService)
//        }
//    }


}