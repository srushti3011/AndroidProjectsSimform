package com.example.learningproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ExpandableRvMovieExerciseBinding

class MovieExpandableRVAdapter(
    private val movies: List<Movies>
): RecyclerView.Adapter<MovieExpandableRVAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ExpandableRvMovieExerciseBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieItem: Movies) {
            binding.tvMovieHeading.text = movieItem.name
            binding.movieDescription.text = movieItem.description
            if(movieItem.isVisible) {
                binding.movieDescription.visibility = View.VISIBLE
            } else {
                binding.movieDescription.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExpandableRvMovieExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position])
        holder.binding.tvMovieHeading.setOnClickListener {
            movies[position].isVisible = !movies[position].isVisible
            notifyItemChanged(position)
        }
    }
}