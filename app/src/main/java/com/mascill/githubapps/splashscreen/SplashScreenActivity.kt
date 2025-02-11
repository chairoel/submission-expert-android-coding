package com.mascill.githubapps.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.mascill.githubapps.MainActivity
import com.mascill.githubapps.core.R
import com.mascill.githubapps.core.ui.viewmodel.ThemeViewModel
import com.mascill.githubapps.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val themeViewModel: ThemeViewModel by viewModel()

    private lateinit var binding: ActivitySplashScreenBinding

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayAppVersion()

        themeViewModel.getTheme().observe(this@SplashScreenActivity) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.ivSplashScreenLogo.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.github_logo_inverse)
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.ivSplashScreenLogo.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.github_logo)
                )
            }

            activityScope.launch(Dispatchers.Main) {
                delay(2000)
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    private fun displayAppVersion() {
        binding.apply {
            try {
                val pInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
                tvAppVersion.text = buildString {
                    append("V ")
                    append(pInfo.versionName)
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}