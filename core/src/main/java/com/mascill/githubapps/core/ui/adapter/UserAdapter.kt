package com.mascill.githubapps.core.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mascill.githubapps.core.R
import com.mascill.githubapps.core.databinding.ItemLayoutUserBinding
import com.mascill.githubapps.core.domain.model.User

class UserAdapter(
    private val onItemClickCallback: RecyclerViewClickListener
) : ListAdapter<User, UserAdapter.ViewHolder>(object : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}) {

    class ViewHolder(val binding: ItemLayoutUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        Log.d("UserAdapter", "onBindViewHolder: data ${user}")
        with(holder) {
            binding.tvUsername.text = user.login
            binding.tvProfileType.text = user.type
            when (user.type) {
                "User" -> binding.ivUserType.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.bg_user_type)
                )
                "Organization" -> binding.ivUserType.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.bg_organization_type)
                )
                else -> binding.ivUserType.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.bg_other_type)
                )
            }

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions().fitCenter())
                .into(binding.ciUserPhoto)

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(user)
            }
        }
    }
}
