package com.example.tamueats.retrofit

import com.example.tamueats.dataclass.MealClass
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealClass>
}