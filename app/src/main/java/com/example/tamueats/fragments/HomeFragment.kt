package com.example.tamueats.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.tamueats.R
import com.example.tamueats.activities.MealActivity
import com.example.tamueats.databinding.FragmentHomeBinding
import com.example.tamueats.dataclass.Meal
import com.example.tamueats.dataclass.MealClass
import com.example.tamueats.retrofit.RetrofitInstance
import com.example.tamueats.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    companion object{
        const val MEAL_ID = "com.example.tamueats.fragments.idMeal"
        const val MEAL_NAME = "com.example.tamueats.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.tamueats.fragments.thumbMeal"
        const val MEAL_VIDEO = "com.example.tamueats.fragments.videoMeal"
        const val MEAL_INSTRUCTION = "com.example.tamueats.fragments.instructionMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = androidx.lifecycle.ViewModelProvider(this)[HomeViewModel::class.java]
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
        homemvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMeal()

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


}