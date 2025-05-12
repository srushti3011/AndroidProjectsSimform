package com.example.learningproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.RecyclerViewItemBinding

class UserRecyclerViewAdapter(
    private val users: List<User>,
    private val onShowDetails: (User) -> Unit
): RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolderClass>() {

//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }
    private lateinit var binding: RecyclerViewItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        Log.i("ViewHolderClass", "onCreateViewHolder")
        binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderClass(binding, onShowDetails)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        Log.i("ViewHolderClass", "onBindViewHolder $position")
        val dataItem = users[position]
        holder.bindData(dataItem)
    }

    class ViewHolderClass(
        val binding: RecyclerViewItemBinding,
        private val onShowDetails: (User) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        companion object {
            var count = 0
        }
        init {
            count = count + 1
            Log.i("ViewHolderClass", "instantiation happening $count")
        }

        fun bindData(user: User) {
            binding.tvUserName.text = user.name
            binding.btnShowUser.setOnClickListener {
                onShowDetails(user)
            }
//            binding.personImg.setImageResource(user.img)
        }
    }
}