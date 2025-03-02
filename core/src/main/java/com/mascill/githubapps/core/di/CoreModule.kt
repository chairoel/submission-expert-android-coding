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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<GithubDatabase>().userDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java, "Github_apps.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.github.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/1EkvzibgiE3k+xdsv+7UU5vhV8kdFCQiUiFdMX5Guuk=")
            .add(hostname, "sha256/6YBE8kK4d5J1qu1wEjyoKqzEIvyRY5HyM/NB2wKdcZo=")
            .add(hostname, "sha256/ICGRfpgmOUXIWcQ/HXPLQTkFPEFPoDyjvH7ohhQpjzs=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single { LocalDataSource(get()) }
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