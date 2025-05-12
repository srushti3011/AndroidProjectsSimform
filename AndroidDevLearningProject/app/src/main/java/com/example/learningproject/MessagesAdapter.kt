package com.example.learningproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.learningproject.databinding.ReceivedMessageBinding
import com.example.learningproject.databinding.SentMessageBinding

class MessagesAdapter(
    private val messageArray: Array<MessageItem>
): RecyclerView.Adapter<ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
        when (messageArray[position].type) {
            MessageType.Sent -> return 0
            MessageType.Received -> return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (MessageType.values()[viewType]) {
            MessageType.Sent -> {
                val binding = SentMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SentViewHolderClass(binding)
            }
            MessageType.Received -> {
                val binding = ReceivedMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ReceivedViewHolderClass(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return messageArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is SentViewHolderClass) {
            holder.bindData(messageArray[position])
        } else if (holder is ReceivedViewHolderClass) {
            holder.bindData(messageArray[position])
        }
    }

    class SentViewHolderClass(
        val binding: SentMessageBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val maxCharacters = 170

        fun bindData(message: MessageItem) {
            if (message.content.count() > maxCharacters) {
                binding.btnReadToggle.visibility = View.VISIBLE
                val trimmedMessage = message.content.substring(0,maxCharacters + 1) + "..."
                binding.tvMessage.text = trimmedMessage

                binding.btnReadToggle.setOnClickListener {
                    if (binding.btnReadToggle.text == "Read More") {
                        binding.tvMessage.text = message.content
                        binding.btnReadToggle.text = "Read Less"
                    } else {
                        val trimmedMessage = message.content.substring(0,maxCharacters + 1) + "..."
                        binding.tvMessage.text = trimmedMessage
                        binding.btnReadToggle.text = "Read More"
                    }
                }
            } else {
                binding.btnReadToggle.visibility = View.GONE
                binding.tvMessage.text = message.content
            }
            binding.tvTime.text = message.time
        }
    }

    class ReceivedViewHolderClass(
        val binding: ReceivedMessageBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val maxCharacters = 170

        fun bindData(message: MessageItem) {
            if (message.content.count() > maxCharacters) {
                binding.btnReadToggle.visibility = View.VISIBLE
                val trimmedMessage = message.content.substring(0,maxCharacters + 1) + "..."
                binding.tvMessage.text = trimmedMessage

                binding.btnReadToggle.setOnClickListener {
                    if (binding.btnReadToggle.text == "Read More") {
                        binding.tvMessage.text = message.content
                        binding.btnReadToggle.text = "Read Less"
                    } else {
                        val trimmedMessage = message.content.substring(0,maxCharacters + 1) + "..."
                        binding.tvMessage.text = trimmedMessage
                        binding.btnReadToggle.text = "Read More"
                    }
                }
            } else {
                binding.btnReadToggle.visibility = View.GONE
                binding.tvMessage.text = message.content
            }
            binding.tvTime.text = message.time
        }
    }
}