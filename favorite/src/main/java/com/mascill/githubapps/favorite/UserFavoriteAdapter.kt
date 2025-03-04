package com.mascill.githubapps.favorite

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

class UserFavoriteAdapter : ListAdapter<User, UserFavoriteAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemLayoutUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemLayoutUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {

            Glide.with(itemView.context)
                .load(data.avatarUrl)
                .apply(RequestOptions().fitCenter())
                .into(binding.ciUserPhoto)

            binding.tvUsername.text = data.login
            binding.tvProfileType.text = data.type
            when (data.type) {
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
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem == newItem
                }
            }
    }
}