package com.mascill.githubapps

import android.app.Application
import com.mascill.githubapps.core.di.databaseModule
import com.mascill.githubapps.core.di.datastoreModule
import com.mascill.githubapps.core.di.networkModule
import com.mascill.githubapps.core.di.repositoryModule
import com.mascill.githubapps.di.useCaseModule
import com.mascill.githubapps.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication:  Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    datastoreModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}