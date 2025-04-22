package com.example.learningproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ExpandableRecyclerItemBinding

class ExpandableRecyclerViewAdapter(
    private val data: Array<ExpandableData>
): RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ExpandableViewHolder>() {

    class ExpandableViewHolder(
        val binding: ExpandableRecyclerItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(dataItem: ExpandableData) {
            binding.imageElement.setImageResource(dataItem.image)
            binding.tvHeadingElement.text = dataItem.title
            binding.tvDescription.text = dataItem.description
            checkVisibilityAndImage(dataItem)
        }
        fun toggleExpanded(dataItem: ExpandableData) {
            dataItem.isExpanded = !dataItem.isExpanded
        }
        private fun checkVisibilityAndImage(dataItem: ExpandableData) {
            if (dataItem.isExpanded) {
                binding.tvDescription.visibility = View.VISIBLE
                binding.imgSymbol.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            } else {
                binding.tvDescription.visibility = View.GONE
                binding.imgSymbol.setImageResource(R.drawable.baseline_arrow_drop_down_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandableViewHolder {
        val binding = ExpandableRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpandableViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExpandableViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.binding.main.setOnClickListener {
            holder.toggleExpanded(data[position])
            notifyItemChanged(position)
        }
    }
}