package com.example.learningproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.SimpleRvItemBinding

class RecycledViewPoolAdapter(
    private val items: List<String>,
//    private val recycled: () -> Unit
): RecyclerView.Adapter<RecycledViewPoolAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: SimpleRvItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(value: String) {
            binding.tvContent.text = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SimpleRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        Log.i("RecyclerView", "onCreateViewHolder")
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("RecyclerView", "onBindViewHolder $position")
        holder.bindData(items[position])
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
//        recycled()
        Log.i("RecyclerView", "onViewRecycled")
    }
}