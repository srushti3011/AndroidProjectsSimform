package com.example.learningproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.RetrofitWithInjection.UserListReqres
import com.example.learningproject.databinding.UserRecyclerItemBinding

class UserListFromApiAdapter(
    val goToDetail: (Int) -> Unit
): RecyclerView.Adapter<UserListFromApiAdapter.UserListFromAdapterVH>(

) {
    var users: List<UserListReqres> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class UserListFromAdapterVH(
        val binding: UserRecyclerItemBinding,
        private val goToDetail: (Int) -> Unit
    ): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindData(user: UserListReqres) {
            binding.apply {
                val fullName = user.firstName + " " + user.lastName
                val id = user.id.toString()
                tvUserName.text = fullName
                tvUserId.text = id
                btnShowUser.setOnClickListener {
                    goToDetail(user.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListFromAdapterVH {
        val binding = UserRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserListFromAdapterVH(binding, goToDetail)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserListFromAdapterVH, position: Int) {
        holder.bindData(users[position])
    }
}