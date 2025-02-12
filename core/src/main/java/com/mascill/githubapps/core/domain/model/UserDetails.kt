package com.mascill.githubapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val id: Long,
    val login: String,
    val company: String,
    val publicRepos: Int,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String,
    val type: String,
    val url: String,
    val location: String
) : Parcelable

