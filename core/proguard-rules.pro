# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Pastikan semua class dalam package core.data tidak dihapus
-keep class com.mascill.githubapps.core.data.** { *; }

# Pastikan semua subclass dari Resource tidak dihapus
-keep class com.mascill.githubapps.core.data.Resource { *; }
-keep class com.mascill.githubapps.core.data.Resource$* { *; }

# Pastikan class ViewBinding tidak dihapus
-keep class androidx.databinding.** { *; }
-keep class com.mascill.githubapps.core.databinding.** { *; }

# Pastikan class Dependency Injection (Koin) tidak dihapus
-keep class org.koin.** { *; }
-keep class com.mascill.githubapps.core.di.** { *; }

# Pastikan class Model, Repository, UseCase, ViewModel tidak dihapus
-keep class com.mascill.githubapps.core.domain.model.** { *; }
-keep class com.mascill.githubapps.core.domain.repository.** { *; }
-keep class com.mascill.githubapps.core.domain.usecase.** { *; }
-keep class com.mascill.githubapps.core.ui.viewmodel.** { *; }
-keep class com.mascill.githubapps.core.ui.adapter.** { *; }

# Pastikan class Util tidak dihapus
-keep class com.mascill.githubapps.core.utils.** { *; }

# Pastikan class untuk DataStore tidak dihapus
-keep class androidx.datastore.** { *; }

# Pastikan class untuk Room Database tidak dihapus
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }

# Pastikan class Retrofit dan GSON tidak dihapus
-keep class com.mascill.githubapps.core.network.** { *; }
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }

# Pastikan semua class yang memiliki fungsi dalam model tetap ada
-keepclassmembers class com.mascill.githubapps.core.domain.model.** {
    *;
}

# Mencegah penghapusan class yang berisi lambda functions
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Pastikan class untuk RecyclerViewClickListener tidak dihapus
-keep class com.mascill.githubapps.core.ui.adapter.RecyclerViewClickListener { *; }

# Pastikan class untuk ItemLayoutUserBinding tidak dihapus
-keep class com.mascill.githubapps.core.databinding.ItemLayoutUserBinding { *; }

# Pastikan class ThemeUseCase dan ThemeInteractor tidak dihapus
-keep class com.mascill.githubapps.core.domain.usecase.theme.ThemeUseCase { *; }
-keep class com.mascill.githubapps.core.domain.usecase.theme.ThemeInteractor { *; }

# Pastikan class UserUseCase dan UserInteractor tidak dihapus
-keep class com.mascill.githubapps.core.domain.usecase.user.UserUseCase { *; }
-keep class com.mascill.githubapps.core.domain.usecase.user.UserInteractor { *; }

# Pastikan class ViewModel tidak dihapus
-keep class com.mascill.githubapps.core.ui.viewmodel.SearchViewModel { *; }
-keep class com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel { *; }
