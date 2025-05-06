package com.example.learningproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.SimpleCellBinding

class NameRecyclerAdapter(
    private var names: List<String>,
): RecyclerView.Adapter<NameRecyclerAdapter.NameViewHolder>(), Filterable {
    class NameViewHolder(
        private val binding: SimpleCellBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(currentString: String) {
            binding.tvContent.text = currentString
        }
    }

    private var namesTemp: List<String> = names
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = SimpleCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return NameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bindData(names[position])
    }

    fun search(text: String) {
        names = namesTemp.filter {
            it.lowercase().contains(text.lowercase() ?: "")
        }
        notifyDataSetChanged()
    } // custom search
    fun lengthGtFive() {
        names = namesTemp.filter {
            it.length > 5
        }
        notifyDataSetChanged()
    }

    fun containsD() {
        this.filter.filter("d")
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                Log.i("Search", "performFiltering")
                names = namesTemp.filter {
                    it.lowercase().contains(constraint?.toString()?.lowercase() ?: "")
                }
                return FilterResults().apply {
                    values = names
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                names = if (results?.values == null)
                    listOf()
                else
                    results.values as List<String>
                notifyDataSetChanged()
            }
        }
    } // using filterable interface
}