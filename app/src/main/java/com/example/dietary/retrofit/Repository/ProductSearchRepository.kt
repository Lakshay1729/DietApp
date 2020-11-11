package com.example.dietary.retrofit.Repository

import androidx.lifecycle.LiveData
import com.example.dietary.retrofit.Service.SpoonacularService
import com.example.dietary.retrofit.data.Recipes.Response
import io.reactivex.Observable

class ProductSearchRepository(val apiService: SpoonacularService) {

    fun searchProducts(query: String, number: Int): LiveData<Response?>? {
        return apiService.groceryProducts(
                query = query,
                number = number

        )
    }
}