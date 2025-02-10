package com.mascill.githubapps.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.mascill.githubapps.core.domain.model.User

class UserDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].login != newList[newItemPosition].login -> {
                false
            }

            oldList[oldItemPosition].avatarUrl != newList[newItemPosition].avatarUrl -> {
                false
            }

            oldList[oldItemPosition].url != newList[newItemPosition].url -> {
                false
            }

            oldList[oldItemPosition].type != newList[newItemPosition].type -> {
                false
            }

            else -> true
        }
    }
}