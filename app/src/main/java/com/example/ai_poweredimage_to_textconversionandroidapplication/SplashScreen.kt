package com.example.ai_poweredimage_to_textconversionandroidapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        // Delay for 3 seconds, then go directly to OnBoardingScreen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardingScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}



