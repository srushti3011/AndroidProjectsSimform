package com.example.learningproject

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.CoffeeItemCellBinding

class CoffeeListingAdapter(
    private val coffeeData: Array<MutableMap<String, Array<CoffeeItem>>>,
    private val selectedCategory: () -> Int,
    private val changeSelectedCoffee: (Int) -> Unit,
    private val context: Context
): RecyclerView.Adapter<CoffeeListingAdapter.CoffeeItemViewHolder>() {
    class CoffeeItemViewHolder(
        val binding: CoffeeItemCellBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(coffee: CoffeeItem) {
            binding.apply {
                tvCoffeeName.text = coffee.coffeeName
                tvCoffeeType.text = coffee.descriptionSmall
                imgCoffee.setImageResource(coffee.imgName)
                tvCoffeePrice.text = "$ ${coffee.price}"
                tvRating.text = coffee.rating.toString()
            }
        }

        fun setUpLike(context: Context) {
            binding.likeContainer.background = ContextCompat
                .getDrawable(context, R.drawable.liked_rating_view_bg)
        }

        fun setUpNotLike(context: Context) {
            binding.likeContainer.background = ContextCompat
                .getDrawable(context, R.drawable.rating_view_bg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeItemViewHolder {
        val binding = CoffeeItemCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoffeeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val selectedCat = currentSelectedCategory()
        Log.i("categoryChanged", "${coffeeData[selectedCat].entries} after refreshing")
        return coffeeData[selectedCat].values.toTypedArray()[0].size
    }

    override fun onBindViewHolder(holder: CoffeeItemViewHolder, position: Int) {
        val selectedCat = currentSelectedCategory()
        holder.bindData(coffeeData[selectedCat].values.toTypedArray()[0][position])
        holder.binding.itemContainer.setOnClickListener {
            changeSelectedCoffee(position)
        }
        if (coffeeData[selectedCat].values.toTypedArray()[0][position].isLiked) {
            holder.setUpLike(context)
        } else {
            holder.setUpNotLike(context)
        }
    }

    private fun currentSelectedCategory(): Int {
        return selectedCategory()
    }
}