package com.example.tamueats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamueats.dataclass.Category
import com.example.tamueats.dataclass.CategoryList
import com.example.tamueats.dataclass.MealsByCategoryList
import com.example.tamueats.dataclass.MealsByCategory
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.dataclass.MealClass
import com.example.tamueats.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()


    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealClass> {
            override fun onResponse(call: Call<MealClass>, response: Response<MealClass>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                    Log.d("TEST", "Meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealClass>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems() {
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body() != null) {
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeVVM", t.message.toString())
            }
        })
    }

    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.isSuccessful && response.body() != null) {
                    val categories = response.body()!!.categories
                    Log.d("HomeViewModel", "Categories fetched: ${categories.size}")
                    categoriesLiveData.postValue(categories)
                } else {
                    Log.e("HomeViewModel", "Failed to fetch categories: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("HomeViewModel", "API call failed: ${t.message}")
            }
        })
    }
    fun observeRandomLiveDate(): LiveData<Meal>{
        return randomMealLiveData
    }

    fun observePopularMeals(): LiveData<List<MealsByCategory>> {
        return popularItemsLiveData

    }
    fun observeCategoryLiveData(): LiveData<List<Category>>{
        return categoriesLiveData
    }
}