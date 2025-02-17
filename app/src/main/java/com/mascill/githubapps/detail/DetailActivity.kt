package com.mascill.githubapps.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mascill.githubapps.R
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.core.utils.parcelable
import com.mascill.githubapps.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
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

        supportActionBar?.title = "Detail User"
        showData(data)
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

    private fun showData(data: User) {
        Glide.with(this@DetailActivity)
            .load(data.avatarUrl)
            .apply(RequestOptions().fitCenter())
            .into(binding.ciUserPhoto)

        var statusFavorite = data.isFavorite
        setStatusFavorite(statusFavorite)
        binding.fabFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteTourism(data, statusFavorite)
            setStatusFavorite(statusFavorite)
        }

        binding.tvUsername.text = data.login
        binding.tvType.text = data.type
    }

    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }
}