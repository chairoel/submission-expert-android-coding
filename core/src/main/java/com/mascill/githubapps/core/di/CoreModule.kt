package com.mascill.githubapps.core.di

import androidx.room.Room
import com.mascill.githubapps.core.data.ThemeRepository
import com.mascill.githubapps.core.data.UserRepository
import com.mascill.githubapps.core.data.source.datastore.DataStoreSource
import com.mascill.githubapps.core.data.source.datastore.dataStore
import com.mascill.githubapps.core.data.source.local.LocalDataSource
import com.mascill.githubapps.core.data.source.local.room.GithubDatabase
import com.mascill.githubapps.core.data.source.remote.RemoteDataSource
import com.mascill.githubapps.core.data.source.remote.network.ApiService
import com.mascill.githubapps.core.domain.repository.IThemeRepository
import com.mascill.githubapps.core.domain.repository.IUserRepository
import com.mascill.githubapps.core.utils.AppExecutors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<GithubDatabase>().userDao() }
    factory { get<GithubDatabase>().userDetailsDao() }
    factory { get<GithubDatabase>().userFavoriteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java, "Github_apps.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("token", "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")
                .build()
            chain.proceed(requestHeaders)
        }
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val datastoreModule = module {
    single {
        DataStoreSource(androidContext().dataStore)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IUserRepository> {
        UserRepository(
            get(),
            get(),
            get()
        )
    }
    single<IThemeRepository> {
        ThemeRepository(get())
    }
}