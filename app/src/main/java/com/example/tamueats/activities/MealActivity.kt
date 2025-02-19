package com.example.tamueats.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.tamueats.R
import com.example.tamueats.databinding.ActivityMealBinding
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.fragments.HomeFragment
import com.example.tamueats.viewmodel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var videoMeal: String
    private lateinit var youTubeLink: String
   // private lateinit var instructionMeal: String

    //    private lateinit var instructionMeal: String
    private lateinit var mealMvvm: MealViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mealMvvm = androidx.lifecycle.ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInfoInView()
        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observeMealDetailsLiveData()
        onYouTubeClick()
    }

    private fun onYouTubeClick() {
        binding.youtube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private fun observeMealDetailsLiveData() {
        mealMvvm.observeLiveData().observe(this
        ) { value ->
            responseCase()
            binding.category.text = "Category: ${value!!.strCategory}"
            binding.area.text = "Area: ${value!!.strArea}"
            binding.tvInstructions.text = value.strInstructions
            youTubeLink = value.strYoutube
        }
    }

    private fun setInfoInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.ivCollapsableImage)
        binding.collapsing.title = mealName
        binding.collapsing.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsing.setExpandedTitleColor(resources.getColor(R.color.white))
    // binding.tvInstructions.text = instructionMeal

    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        Log.d("MealActivity", "Intent Extras: ${intent.extras}")
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID) ?: run {
            Log.e("MealActivity", "MEAL_ID is null")
            finish()
            return
        }
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME) ?: ""
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB) ?: ""
        videoMeal = intent.getStringExtra(HomeFragment.MEAL_VIDEO) ?: ""
       // instructionMeal = intent.getStringExtra(HomeFragment.MEAL_INSTRUCTION) ?:""
    }

//    private fun getMealInformationFromIntent() {
//        val intent = intent
//        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
//        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
//        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
//        videoMeal = intent.getStringExtra(HomeFragment.MEAL_VIDEO)!!
////        instructionMeal = intent.getStringExtra(HomeFragment.MEAL_INSTRUCTION)!!
//
//    }

    private fun loadingCase() {
        binding.apply {
            progressbar.visibility = View.VISIBLE
            favouriteFloatingButton.visibility = View.INVISIBLE
            tvInstructions.visibility = View.INVISIBLE
            category.visibility = View.INVISIBLE
            area.visibility = View.INVISIBLE
            youtube.visibility = View.INVISIBLE
        }

    }

    private fun responseCase() {
        binding.apply {
            progressbar.visibility = View.INVISIBLE
            favouriteFloatingButton.visibility = View.VISIBLE
            tvInstructions.visibility = View.VISIBLE
            category.visibility = View.VISIBLE
            area.visibility = View.VISIBLE
            youtube.visibility = View.VISIBLE
        }
    }
}