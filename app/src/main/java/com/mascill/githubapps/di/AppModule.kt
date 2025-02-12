package com.mascill.githubapps.di

import com.mascill.githubapps.core.domain.usecase.theme.ThemeInteractor
import com.mascill.githubapps.core.domain.usecase.theme.ThemeUseCase
import com.mascill.githubapps.core.domain.usecase.UserInteractor
import com.mascill.githubapps.core.domain.usecase.UserUseCase
import com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel
import com.mascill.githubapps.detail.DetailViewModel
import com.mascill.githubapps.home.HomeViewModel
import com.mascill.githubapps.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
    factory<ThemeUseCase> { ThemeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { ThemeViewModel(get()) }
}