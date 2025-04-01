package com.example.tamueats.retrofit

import androidx.lifecycle.ViewModelProvider
import com.example.tamueats.db.MealDatabase

class MealViewModelFactory(
    val mealDatabase: MealDatabase
): ViewModelProvider.Factory {

}