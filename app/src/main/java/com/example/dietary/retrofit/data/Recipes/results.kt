package com.example.dietary.retrofit.data.Recipes

data class results(
        val id:Int?=0,
        val title: String?=null,
        val image: String?=null,
        val imageType: String?=null,
        val nutrition: Nutrition?=null
)