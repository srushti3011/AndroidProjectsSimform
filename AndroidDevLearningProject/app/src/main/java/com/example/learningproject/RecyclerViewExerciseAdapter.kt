package com.example.learningproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ExerciseRecyclerItemBinding

class RecyclerViewExerciseAdapter(
    private val data: List<RecyclerExerciseData>
): RecyclerView.Adapter<RecyclerViewExerciseAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ExerciseRecyclerItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(dataItem: RecyclerExerciseData) {
            binding.imgView.setImageResource(dataItem.img)
            binding.tvName.text = dataItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExerciseRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}