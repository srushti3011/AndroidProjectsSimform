package com.example.learningproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ViewPagerRecyclerItemBinding

class ViewPagerRecyclerAdapter(
    private val textCollection: List<String>
): RecyclerView.Adapter<ViewPagerRecyclerAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = ViewPagerRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return textCollection.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        holder.bindDataAndItem(textCollection[position])
    }

    class ViewHolderClass(
        val binding: ViewPagerRecyclerItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindDataAndItem(stringValue: String) {
            binding.tvContent.text = stringValue
        }
    }
}