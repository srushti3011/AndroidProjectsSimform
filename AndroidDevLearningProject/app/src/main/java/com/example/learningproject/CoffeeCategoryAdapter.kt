package com.example.learningproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.CoffeeCategoryCellBinding

class CoffeeCategoryAdapter(
    private val categories: Array<String>,
    private val changeSelectedCategory: (Int) -> Unit,
    private val selectedCategory: () -> Int,
    private val context: Context
): RecyclerView.Adapter<CoffeeCategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(
        val binding: CoffeeCategoryCellBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindData(categoryName: String) {
            binding.categoryNameButton.text = categoryName
        }

        fun setSelectedCategoryBackground(context: Context) {
            binding.categoryNameButton.setTextColor(
                ContextCompat.getColor(context, R.color.white)
            )
            binding.categoryNameButton.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.coffee_selected_category_bg
                )
            )
        }

        fun setNotSelectedCategoryBackground(context: Context) {
            binding.categoryNameButton.setTextColor(ContextCompat.getColor(context, R.color.black))
            binding.categoryNameButton.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.coffee_category_button_bg
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CoffeeCategoryCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.i("categoryChanged", "onBindViewHolder $position")
        val selectedCat = selectedCategory()
        holder.apply {
            bindData(categories[position])
            binding.categoryNameButton.setOnClickListener {
                changeSelectedCategory(position)
            }
            if (position == selectedCat) {
                setSelectedCategoryBackground(context)
            } else {
                setNotSelectedCategoryBackground(context)
            }
        }
    }
}