package com.mascill.githubapps.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.ui.adapter.RecyclerViewClickListener
import com.mascill.githubapps.core.ui.adapter.UserAdapter
import com.mascill.githubapps.core.ui.viewmodel.SearchViewModel
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.core.utils.hideLoading
import com.mascill.githubapps.core.utils.showLoading
import com.mascill.githubapps.databinding.FragmentHomeBinding
import com.mascill.githubapps.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), RecyclerViewClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            with(binding) {
                searchView.setupWithSearchBar(searchBar)
                searchView.editText.setOnEditorActionListener { _, _, _ ->
                    val username = searchView.text.toString()
                    searchBar.setText(username)
                    searchView.hide()
                    if (username.isEmpty()) {
                        homeViewModel.fetchUsers()
                    } else {
                        searchViewModel.searchUsers(username)
                    }
                    true
                }

                userAdapter = UserAdapter(this@HomeFragment)
                rvUser.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = userAdapter
                }
            }

            homeViewModel.users.observe(viewLifecycleOwner) { users ->
                if (users != null) {
                    when (users) {
                        is Resource.Loading -> {
                            showLoading(binding.loading)
                        }

                        is Resource.Success -> {
                            hideLoading(binding.loading)
                            users.data?.let { data ->
                                userAdapter.submitList(data)
                                binding.tvEmptyData.visibility =
                                    if (data.isEmpty()) View.VISIBLE else View.GONE
                            }
                        }

                        is Resource.Error -> {
                            hideLoading(binding.loading)
                        }
                    }
                }
            }

            searchViewModel.searchResults.observe(viewLifecycleOwner){users ->
                if (users != null){
                    userAdapter.submitList(users)
                    binding.tvEmptyData.visibility =
                        if (users.isEmpty()) View.VISIBLE else View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvUser.adapter = null
        _binding = null
    }

    override fun onItemClicked(user: User) {
        val detailIntent = Intent(requireContext(), DetailActivity::class.java)
        detailIntent.putExtra(Constant.USER_DATA, user)
        startActivity(detailIntent)
    }
}