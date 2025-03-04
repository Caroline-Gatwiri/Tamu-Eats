package com.example.tamueats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tamueats.databinding.MealItemBinding
import com.example.tamueats.dataclass.MealsByCategory

class CategoryMealAdapter : RecyclerView.Adapter<CategoryMealAdapter.CategoryMealViewHolder>() {

    private var mealsList = ArrayList<MealsByCategory>()

    fun setMealsList(mealList: List<MealsByCategory>) {
        this.mealsList = mealList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    inner class CategoryMealViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryMealViewHolder {
        return CategoryMealViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CategoryMealViewHolder,
        position: Int
    ) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imageMeal)
        holder.binding.mealName.text = mealsList[position].strMeal
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}