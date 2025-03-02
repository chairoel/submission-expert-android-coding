package com.mascill.githubapps.di

import com.mascill.githubapps.core.domain.usecase.theme.ThemeInteractor
import com.mascill.githubapps.core.domain.usecase.theme.ThemeUseCase
import com.mascill.githubapps.core.domain.usecase.user.UserInteractor
import com.mascill.githubapps.core.domain.usecase.user.UserUseCase
import com.mascill.githubapps.core.ui.viewmodel.SearchViewModel
import com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel
import com.mascill.githubapps.detail.DetailViewModel
import com.mascill.githubapps.detail.FavoriteDetailViewModel
import com.mascill.githubapps.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
    factory<ThemeUseCase> { ThemeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteDetailViewModel(get()) }
    viewModel { ThemeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}