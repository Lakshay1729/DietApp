package com.example.dietary.retrofit.data.Recipes

data class Response(
val results:List<results>,
val offset:Int?=0,
val number:Int?=0,
val totalResults:Int?=0
)