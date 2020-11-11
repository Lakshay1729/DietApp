package com.example.dietary.retrofit.Service

import androidx.lifecycle.LiveData
import com.example.dietary.retrofit.data.Recipes.Response
import com.example.dietary.retrofit.data.nutrients.NutrientsResponse
//import com.example.dietary.retrofit.data.Recipes
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

public interface SpoonacularService {
    @GET("recipes/complexSearch")
    fun search(@Query("apiKey") apiKey: String? = "574fdb67b16744d388fc802b06bf53fa", @Query("query") query: String,@Query("maxFat") maxFat: Int, @Query("number") number: Int): Observable<Response?>?


    @GET("food/products/search")
    fun groceryProducts(@Query("apiKey") apiKey: String?="574fdb67b16744d388fc802b06bf53fa",@Query("query")query:String,@Query("number") number:Int):LiveData<Response?>?


    @GET("recipes/findByNutrients")
    fun findByNutrients(@Query("apiKey") apiKey: String?="574fdb67b16744d388fc802b06bf53fa",@Query("minCarbs")minCarbs:Int,@Query("maxCarbs") maxCarbs:Int,@Query("minProtein") minProtein:Int,@Query("maxProtein") maxProtein:Int,@Query("minCalories")minCalories:Int,@Query("maxCalories")maxCalories:Int,@Query("minFat")minFat:Int,@Query("maxfat")maxfat:Int,@Query("number")number:Int):LiveData<NutrientsResponse?>?




    companion object Factory {
        fun create(): SpoonacularService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.spoonacular.com/")
                    .build()
            return retrofit.create<SpoonacularService>(SpoonacularService::class.java)
        }
    }

}