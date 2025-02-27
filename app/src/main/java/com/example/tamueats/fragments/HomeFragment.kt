package com.example.tamueats.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tamueats.activities.CategoryMealsActivity
import com.example.tamueats.activities.MealActivity
import com.example.tamueats.adapters.CategoriesAdapter
import com.example.tamueats.adapters.MostPopularMealAdapter
import com.example.tamueats.databinding.FragmentHomeBinding
import com.example.tamueats.dataclass.MealsByCategory
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemAdapter: MostPopularMealAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter


    companion object{
        const val MEAL_ID = "com.example.tamueats.fragments.idMeal"
        const val MEAL_NAME = "com.example.tamueats.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.tamueats.fragments.thumbMeal"
        const val MEAL_VIDEO = "com.example.tamueats.fragments.videoMeal"
        const val MEAL_INSTRUCTION = "com.example.tamueats.fragments.instructionMeal"
        const val CATEGORY_NAME = "com.example.tamueats.fragments.categoryName"



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = androidx.lifecycle.ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemAdapter = MostPopularMealAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemsRecyclerView()

        homemvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMeal()

        homemvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

        prepareCategoriesRecyclerView()
        homemvvm.getCategories()
        observeCategoriesLiveData()

        onCategoryClick()



    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {category->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        homemvvm.observeCategoryLiveData().observe(viewLifecycleOwner, Observer { categories ->
            if (categories != null) {
                Log.d("HomeFragment", "Categories observed: ${categories.size}")
                categoriesAdapter.setCategoryList(categories)
            } else {
                Log.e("HomeFragment", "Categories list is null")
            }
        })
    }

    private fun onPopularItemClick() {
        popularItemAdapter.onItemClick = {meal ->

val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }

    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewPopulate.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        homemvvm.observePopularMeals().observe(viewLifecycleOwner
        ) {mealList ->
            popularItemAdapter.setMeals(mealList = mealList as ArrayList<MealsByCategory>)

        }
    }

    private fun observeRandomMeal() {
        homemvvm.observeRandomLiveDate().observe(
            viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal.strMealThumb)
                .into(binding.randomMeal)

            this.randomMeal = meal
        }
    }

    private fun onRandomMeal() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            intent.putExtra(MEAL_VIDEO,randomMeal.strYoutube)
            intent.putExtra(MEAL_INSTRUCTION, randomMeal.strInstructions)
            startActivity(intent)
        }
    }



}