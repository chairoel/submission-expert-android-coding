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
