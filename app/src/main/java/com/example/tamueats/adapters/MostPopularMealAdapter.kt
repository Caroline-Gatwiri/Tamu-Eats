package com.example.tamueats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tamueats.databinding.PopularitemsBinding
import com.example.tamueats.dataclass.MealsByCategory

class MostPopularMealAdapter() : RecyclerView.Adapter<MostPopularMealAdapter
.PopularMealViewHolder>() {

    private var mealList = ArrayList<MealsByCategory>()
    lateinit var onItemClick:((MealsByCategory) -> Unit)

    fun setMeals(mealList: ArrayList<MealsByCategory>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularitemsBinding.inflate(LayoutInflater.from(parent
            .context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.popularMeaImage)
        holder.binding.popularMeaImage.setOnClickListener {
            onItemClick.invoke(mealList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class PopularMealViewHolder( val binding: PopularitemsBinding) : RecyclerView.ViewHolder
        (binding.root)

}