# Keep Resource classes
-keep class com.mascill.githubapps.core.data.Resource$Error { *; }
-keep class com.mascill.githubapps.core.data.Resource$Loading { *; }
-keep class com.mascill.githubapps.core.data.Resource$Success { *; }
-keep class com.mascill.githubapps.core.data.Resource { *; }

# Keep domain models
-keep class com.mascill.githubapps.core.domain.model.User { *; }
-keep class com.mascill.githubapps.core.domain.model.DetailUser { *; }

# Keep UI-related classes
-keep class com.mascill.githubapps.core.ui.adapter.UserAdapter { *; }
-keep class com.mascill.githubapps.core.ui.adapter.RecyclerViewClickListener { *; }

# Keep view models
-keep class com.mascill.githubapps.core.ui.viewmodel.SearchViewModel { *; }
-keep class com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel { *; }

# Keep data binding classes
-keep class com.mascill.githubapps.core.databinding.ItemLayoutUserBinding { *; }

# Keep other important classes like the DI module
-keep class com.mascill.githubapps.core.di.CoreModuleKt { *; }

# Keep missing classes related to the theme
-keep class com.mascill.githubapps.core.domain.repository.IThemeRepository { *; }
-keep class com.mascill.githubapps.core.domain.repository.IUserRepository { *; }

# Keep the use case classes
-keep class com.mascill.githubapps.core.domain.usecase.theme.ThemeUseCase { *; }
-keep class com.mascill.githubapps.core.domain.usecase.user.UserUseCase { *; }
-keep class com.mascill.githubapps.core.domain.usecase.theme.ThemeInteractor { *; }
-keep class com.mascill.githubapps.core.domain.usecase.user.UserInteractor { *; }
