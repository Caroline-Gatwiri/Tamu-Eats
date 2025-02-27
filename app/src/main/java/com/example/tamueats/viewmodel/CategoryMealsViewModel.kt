package com.example.tamueats.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tamueats.dataclass.CategoryList
import com.example.tamueats.dataclass.MealsByCategory
import com.example.tamueats.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {
//    fun getMealsByCategory(categoryName:String){
//        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<CategoryList> {
//            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
//
//            }
//
//            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
//                Log.d("Test", "Failed to get the categories")
//            }
//        })
//    }
}


