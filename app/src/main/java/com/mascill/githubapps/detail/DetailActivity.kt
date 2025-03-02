package com.mascill.githubapps.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mascill.githubapps.R
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.DetailUser
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.core.utils.convertToK
import com.mascill.githubapps.core.utils.hideLoading
import com.mascill.githubapps.core.utils.parcelable
import com.mascill.githubapps.core.utils.showLoading
import com.mascill.githubapps.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private val favoriteDetailViewModel: FavoriteDetailViewModel by viewModel()
    private lateinit var data: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item: User? = intent.parcelable(Constant.USER_DATA)
        if (item != null) {
            data = item
        }
        val username = item!!.login
        detailViewModel.getDetailUser(username)
        supportActionBar?.title = username

        detailViewModel.detailUser.observe(this@DetailActivity) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(binding.loading)
                    }

                    is Resource.Success -> {
                        hideLoading(binding.loading)
                        it.data?.let { result ->

                            Log.d("TAG", "onCreate: data: $result")
                            showData(result, data)
                        }
                        binding.layoutDetailVisibility.visibility = View.VISIBLE
                        binding.tvEmptyData.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        binding.layoutDetailVisibility.visibility = View.GONE
                        binding.tvEmptyData.visibility = View.VISIBLE
                        hideLoading(binding.loading)
                    }
                }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()

            R.id.menu_share -> {
                try {
                    val body = "Visit this awesome user \n${data.url}"
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, body)
                    startActivity(Intent.createChooser(shareIntent, "Share with:"))
                } catch (e: Exception) {
                    Log.e("shareFailed", "onOptionsItemSelected: ${e.localizedMessage}")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun showData(data: DetailUser, dataUser: User) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(data.avatarUrl)
                .apply(RequestOptions().fitCenter())
                .into(ciUserPhoto)

            tvRepository.text = convertToK(data.publicRepos)
            tvFollowers.text = convertToK(data.followers)
            tvFollowing.text = convertToK(data.following)

            tvUsername.text = data.name
            tvCompany.text = data.company
            tvLocation.text = data.location

            var statusFavorite = dataUser.isFavorite
            setStatusFavorite(fabFavorite, statusFavorite)
            fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                favoriteDetailViewModel.setFavoriteTourism(dataUser, statusFavorite)
                setStatusFavorite(fabFavorite, statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(fabFavorite: FloatingActionButton, statusFavorite: Boolean) {
        if (statusFavorite) {
            fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }
}