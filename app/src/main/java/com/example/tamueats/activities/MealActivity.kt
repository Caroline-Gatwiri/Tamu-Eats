package com.example.tamueats.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.tamueats.R
import com.example.tamueats.databinding.ActivityMainBinding
import com.example.tamueats.databinding.ActivityMealBinding
import com.example.tamueats.fragments.HomeFragment

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var videoMeal: String
    private lateinit var instructionMeal: String
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


        getMealInformationFromIntent()

        setInfoInView()
    }

    private fun setInfoInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.ivCollapsableImage)
        binding.collapsing.title = mealName
        binding.collapsing.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsing.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.tvInstructions.text = instructionMeal

    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
        videoMeal = intent.getStringExtra(HomeFragment.MEAL_VIDEO)!!
        instructionMeal = intent.getStringExtra(HomeFragment.MEAL_INSTRUCTION)!!

    }
}