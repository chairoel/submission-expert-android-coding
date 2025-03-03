package com.mascill.githubapps.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mascill.githubapps.R
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.ui.adapter.RecyclerViewClickListener
import com.mascill.githubapps.core.ui.adapter.UserAdapter
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.detail.DetailActivity
import com.mascill.githubapps.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(), RecyclerViewClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteFragment: FavoriteViewModel by viewModel()
    private val userAdapter by lazy { UserAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.appBar.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.favorite)

        if (activity != null) {

            with(binding) {
                rvUser.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = userAdapter
                }
            }

            favoriteFragment.favoriteUser.observe(viewLifecycleOwner) { users ->
                users.let { data ->
                    userAdapter.submitList(users)
                    binding.tvEmptyData.visibility =
                        if (data.isEmpty()) View.VISIBLE else View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
        binding.rvUser.adapter = null
        _binding = null
    }

    override fun onItemClicked(user: User) {
        val detailIntent = Intent(requireContext(), DetailActivity::class.java)
        detailIntent.putExtra(Constant.USER_DATA, user)
        startActivity(detailIntent)
    }
}