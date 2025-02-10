package com.mascill.githubapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val type: String,
    val url: String,
    val isFavorite:Boolean
) : Parcelable
