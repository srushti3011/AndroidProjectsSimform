package com.example.learningproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ProductItemBinding

class ProductAdapter(
    private var products: List<ProductItem>,
    private val productsForFiltering: List<ProductItem> = products
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {

    class ProductViewHolder(
        private val binding: ProductItemBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindData(product: ProductItem) {
            val price = "Rs. ${product.price}"
            val stars = "Rating - ${product.star}"
            val likes = "Likes - ${product.liked}"
            binding.apply {
                tvProductName.text = product.name
                tvProductPrice.text = price
                tvProductStar.text = stars
                tvProductLikes.text = likes
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                products = productsForFiltering.filter {
                    it.name.lowercase().contains(constraint?.toString()?.lowercase() ?: "")
                }
                return FilterResults().apply {
                    values = products
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                products = if (results?.values == null)
                    listOf()
                else
                    results.values as List<ProductItem>
                notifyDataSetChanged()
            }
        }
    }

    fun filterGtThousand() {
        products = productsForFiltering.filter {
            it.price > 1000
        }
        notifyDataSetChanged()
    }

    fun filterFivestar() {
        products = productsForFiltering.filter {
            it.star == 5.0
        }
        notifyDataSetChanged()
    }

    fun filterHighestPrice() {
        val highestPrice = productsForFiltering.maxBy { it.price }.price
        products = productsForFiltering.filter { it.price == highestPrice }
        notifyDataSetChanged()
    }

    fun filterMostLiked() {
        val highestLikes = productsForFiltering.maxBy { it.liked }.liked
        products = productsForFiltering.filter { it.liked == highestLikes }
        notifyDataSetChanged()
    }
}
