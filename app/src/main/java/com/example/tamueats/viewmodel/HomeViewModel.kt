package com.example.tamueats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.dataclass.MealClass
import com.example.tamueats.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
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

    fun observeRandomLiveDate(): LiveData<Meal>{
        return randomMealLiveData
    }
}