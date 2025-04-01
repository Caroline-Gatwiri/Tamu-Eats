package com.example.tamueats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.dataclass.MealClass
import com.example.tamueats.db.MealDatabase
import com.example.tamueats.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealViewModel(
   val mealDatabase: MealDatabase
) : ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()


    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealClass> {
            override fun onResponse(call: Call<MealClass>, response: Response<MealClass>) {
                if (response.body() != null) {
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }
            }

            override fun onFailure(call: Call<MealClass>, t: Throwable) {
                Log.d("MeaActivity", t.message.toString())
            }
        })
    }

    fun observeLiveData(): LiveData<Meal> {
        return mealDetailsLiveData
    }

    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }

    fun delete(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }

}