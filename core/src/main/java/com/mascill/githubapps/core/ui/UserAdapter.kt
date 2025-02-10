package com.mascill.githubapps.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mascill.githubapps.core.R
import com.mascill.githubapps.core.databinding.ItemLayoutUserBinding
import com.mascill.githubapps.core.domain.model.User

class UserAdapter(
    private var onItemClickCallback: RecyclerViewClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var oldList = emptyList<User>()

    class ViewHolder(val binding: ItemLayoutUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(oldList[position]) {
                binding.tvUsername.text = login
                binding.tvProfileType.text = type
                when (type) {
                    "User" -> {
                        binding.ivUserType.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.bg_user_type
                            )
                        )
                    }

                    "Organization" -> {
                        binding.ivUserType.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.bg_organization_type
                            )
                        )
                    }

                    else -> binding.ivUserType.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.bg_other_type
                        )
                    )
                }
                Glide.with(itemView.context)
                    .load(avatarUrl)
                    .apply(RequestOptions().fitCenter())
                    .into(binding.ciUserPhoto)
                binding.itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun getItem(index: Int): User {
        return oldList[index]
    }

    fun setItems(newList: List<User>) {
        val diffUtil = UserDiffCallback(oldList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResults.dispatchUpdatesTo(this)
    }
}