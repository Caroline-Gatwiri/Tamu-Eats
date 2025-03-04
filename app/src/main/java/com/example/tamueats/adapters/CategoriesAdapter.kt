package com.example.tamueats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tamueats.databinding.CategoryitemBinding
import com.example.tamueats.dataclass.Category

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    fun setCategoryList(categoryList: List<Category>) {
        this.categoriesList.clear()
        this.categoriesList.addAll(categoryList)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryitemBinding) : RecyclerView.ViewHolder
        (binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryitemBinding.inflate(
                LayoutInflater.from(
                    parent
                        .context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        Glide.with(holder.itemView)
            .load(category.strCategoryThumb)
            .into(holder.binding.imageCategory)
        holder.binding.tvcategoryName.text = category.strCategory

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}