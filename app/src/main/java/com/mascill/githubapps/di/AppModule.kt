package com.mascill.githubapps.di

import com.mascill.githubapps.core.domain.usecase.UserInteractor
import com.mascill.githubapps.core.domain.usecase.UserUseCase
import com.mascill.githubapps.home.HomeViewModel
import com.mascill.githubapps.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel() }
//    viewModel { DetailTourismViewModel(get()) }
}