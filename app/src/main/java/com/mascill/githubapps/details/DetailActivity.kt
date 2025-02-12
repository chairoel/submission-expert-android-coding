package com.mascill.githubapps.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mascill.githubapps.R
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.model.UserDetails
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.core.utils.convertToK
import com.mascill.githubapps.core.utils.hideLoading
import com.mascill.githubapps.core.utils.parcelable
import com.mascill.githubapps.core.utils.showLoading
import com.mascill.githubapps.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var buttonState: Boolean = false
    private lateinit var data: User

    private val viewModel: DetailViewModel by viewModel()

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

//        viewModel.getDetailUser(username)
        supportActionBar?.title = username

        viewModel.getDetails(username).observe(this@DetailActivity) { details ->
            if (details != null) {
                when (details) {
                    is Resource.Loading -> {
                        showLoading(binding.loading)
                    }

                    is Resource.Success -> {
                        hideLoading(binding.loading)
                        details.data?.let { data ->
                            showData(data)
                        }
                        binding.layoutDetailVisibility.visibility = View.VISIBLE
                        binding.tvEmptyData.visibility = View.GONE
                    }

                    is Resource.Error -> {
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

    private fun showData(data: UserDetails) {
        with(binding) {

            Glide.with(this@DetailActivity)
                .load(data.avatarUrl)
                .apply(RequestOptions().fitCenter())
                .into(binding.ciUserPhoto)

            tvRepository.text = convertToK(data.publicRepos)
            tvFollowers.text = convertToK(data.followers)
            tvFollowing.text = convertToK(data.following)

            tvUsername.text = data.name
            tvCompany.text = data.company
            tvLocation.text = data.location

            val dataFavorite = UserEntity(
                id = data.id,
                login = data.login,
                avatarUrl = data.avatarUrl,
                url = data.url,
                type = data.type
            )

            // Favorite event
            fabFavorite.setOnClickListener {
                if (!buttonState) {
                    buttonState = true
                    fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
//                    favoriteViewModel.saveFavoriteUser(dataFavorite)
                } else {
                    buttonState = false
                    fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
//                    favoriteViewModel.deleteFavoriteUser(dataFavorite)
                    Toast.makeText(
                        this@DetailActivity,
                        "Favorite user has been deleted.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}