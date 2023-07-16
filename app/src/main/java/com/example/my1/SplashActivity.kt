package com.example.my1

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val lottie=findViewById<LottieAnimationView>(R.id.lottie)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, Artcles::class.java)
                startActivity(intent)
                finish()
            },4200)
    }
}