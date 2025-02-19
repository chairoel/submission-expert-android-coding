package com.mascill.githubapps.core.ui.adapter

import com.mascill.githubapps.core.domain.model.User

interface RecyclerViewClickListener {
    fun onItemClicked(user: User)
}