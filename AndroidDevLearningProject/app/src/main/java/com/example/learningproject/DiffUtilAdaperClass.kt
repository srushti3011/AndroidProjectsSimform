package com.example.learningproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.SimpleRvItemBinding

class DiffUtilAdaperClass(
    private var people: List<PersonItem>
): RecyclerView.Adapter<DiffUtilAdaperClass.ViewHolder>() {
    class ViewHolder(
        val binding: SimpleRvItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(person: PersonItem) {
            binding.tvContent.text = person.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SimpleRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        Log.i("DiffUtil", "onCreateViewHolder")
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("DiffUtil", "onBindViewHolder")
        holder.bindData(people[position])
    }

    fun updateData(peopleNew: List<PersonItem>) {
        val diffUtil = DiffUtilClass(people, peopleNew)
        val result = DiffUtil.calculateDiff(diffUtil)
        people = peopleNew
        personData = peopleNew
        result.dispatchUpdatesTo(this)
    }
}