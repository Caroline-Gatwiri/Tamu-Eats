package com.example.tamueats.retrofit

import com.example.tamueats.dataclass.CategoryList
import com.example.tamueats.dataclass.MealsByCategoryList
import com.example.tamueats.dataclass.MealClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealClass>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i")id: String) :Call<MealClass>

    @GET("filter.php?")
    fun getPopularItems(@Query("c")categoryName: String) :Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>
}