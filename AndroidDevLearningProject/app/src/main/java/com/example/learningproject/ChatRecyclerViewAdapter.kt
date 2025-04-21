package com.example.learningproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.DateSectionItemBinding

class ChatRecyclerViewAdapter(
    private val messages: Array<MessageCollection>,
): RecyclerView.Adapter<ChatRecyclerViewAdapter.SectionViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolderClass {
        val binding = DateSectionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SectionViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: SectionViewHolderClass, position: Int) {
        holder.bindData(messages[position])
    }

    class SectionViewHolderClass(
        val binding: DateSectionItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindData(currentDayMessages: MessageCollection) {
            binding.tvDate.text = currentDayMessages.date
            binding.messageRecyclerView.adapter = MessagesAdapter(currentDayMessages.messages)
        }
    }
}