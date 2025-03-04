package com.example.tamueats.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tamueats.R
import com.example.tamueats.adapters.CategoryMealAdapter
import com.example.tamueats.databinding.ActivityCategoryMealsBinding
import com.example.tamueats.databinding.ActivityMainBinding
import com.example.tamueats.dataclass.MealsByCategory
import com.example.tamueats.dataclass.MealsByCategoryList
import com.example.tamueats.fragments.HomeFragment
import com.example.tamueats.viewmodel.CategoriesViewModel
import com.example.tamueats.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryViewModel: CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prepareRecyclerView()
//        categoryViewModel = androidx.lifecycle.ViewModelProvider(this)[CategoriesViewModel::class.java]
        categoryViewModel = androidx.lifecycle.ViewModelProvider(this)[CategoryMealsViewModel::class.java]
categoryViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryViewModel.observeMealLiveData().observe(this, Observer{ mealList->
            binding.categotyCount.text = mealList.size.toString()
categoryMealsAdapter.setMealsList(mealList)


//            mealList.forEach{
//                Log.d("test",it.strMeal)
//            }
        })
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealAdapter()
        binding.ivmeals.apply {
            layoutManager = GridLayoutManager(context, 2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }

}