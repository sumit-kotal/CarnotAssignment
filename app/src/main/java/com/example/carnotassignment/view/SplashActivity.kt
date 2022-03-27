package com.example.carnotassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.carnotassignment.R
import com.example.carnotassignment.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showLoadingScreen()
        supportActionBar?.hide()
        /* Add Delay to show Login/Home Screen after Splash Screen*/
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, Companion.SPLASH_DELAY)
    }

    private fun showLoadingScreen() {
        val hyperspaceJumpAnimation: Animation = AnimationUtils.loadAnimation(
            this, R.anim.hyperspace_anim
        )
        hyperspaceJumpAnimation.duration = ANIMATION_TIME
        hyperspaceJumpAnimation.repeatCount = Animation.INFINITE
        binding.splashImage.startAnimation(hyperspaceJumpAnimation)
    }

    companion object {
        const val SPLASH_DELAY: Long = 3000 //Time delay to remove splash screen
        const val ANIMATION_TIME: Long = 500
    }
}