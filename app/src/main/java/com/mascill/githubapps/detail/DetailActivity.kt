package com.mascill.githubapps.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mascill.githubapps.R
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.utils.Constant
import com.mascill.githubapps.core.utils.parcelable
import com.mascill.githubapps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var buttonState: Boolean = false
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

        supportActionBar?.title = username
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
        with(binding) {

            Glide.with(this@DetailActivity)
                .load(data.avatarUrl)
                .apply(RequestOptions().fitCenter())
                .into(binding.ciUserPhoto)


            val dataFavorite = UserEntity(
                id = data.id,
                login = data.login,
                avatarUrl = data.avatarUrl,
                url = data.url,
                type = data.type,
                isFavorite = false
            )

            // Favorite event
            fabFavorite.setOnClickListener {
                if (!buttonState) {
                    buttonState = true
                    fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    buttonState = false
                    fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
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