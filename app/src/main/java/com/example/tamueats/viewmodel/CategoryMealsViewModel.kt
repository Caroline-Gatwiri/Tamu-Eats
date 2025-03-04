package com.example.tamueats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamueats.dataclass.MealsByCategory
import com.example.tamueats.dataclass.MealsByCategoryList
import com.example.tamueats.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {

    val mealLiveData = MutableLiveData<List<MealsByCategory>>()
    fun getMealsByCategory(categoryName:String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object :
            Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let { mealsList ->
                    mealLiveData.postValue(mealsList.meals)

                }
            }

            override fun onFailure(p0: Call<MealsByCategoryList>, p1: Throwable) {
               Log.e("category Meals view model",p1.message.toString())
            }
        })
    }
    fun observeMealLiveData(): LiveData<List<MealsByCategory>> {
        return mealLiveData
    }
}


