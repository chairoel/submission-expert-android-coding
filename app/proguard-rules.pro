# keep class Resource
-keep class com.mascill.githubapps.core.data.Resource$Error { *; }
-keep class com.mascill.githubapps.core.data.Resource$Loading { *; }
-keep class com.mascill.githubapps.core.data.Resource$Success { *; }
-keep class com.mascill.githubapps.core.data.Resource { *; }

# keep model domain
-keep class com.mascill.githubapps.core.domain.model.User { *; }
-keep class com.mascill.githubapps.core.domain.model.DetailUser { *; }

# keep class UI
-keep class com.mascill.githubapps.core.ui.adapter.UserAdapter { *; }
-keep class com.mascill.githubapps.core.ui.adapter.RecyclerViewClickListener { *; }

# keep class data binding
-keep class com.mascill.githubapps.core.databinding.ItemLayoutUserBinding { *; }

# keep class DI
-keep class com.mascill.githubapps.core.di.CoreModuleKt { *; }

# keep class ViewModel
-keep class com.mascill.githubapps.core.ui.viewmodel.SearchViewModel { *; }
-keep class com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel { *; }
