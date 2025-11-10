package com.example.ai_poweredimage_to_textconversionandroidapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingScreen : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: OnBoardingAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var btnNext: Button
    private lateinit var btnSkip: TextView

    private val PREFS_NAME = "onboarding_prefs"
    private val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_screen)
        supportActionBar?.hide()

        // Initialize UI
        viewPager = findViewById(R.id.onboarding_viewpager)
        tabLayout = findViewById(R.id.tab_indicator)
        btnNext = findViewById(R.id.btn_next)
        btnSkip = findViewById(R.id.btn_skip)

        // Onboarding slides
        val items = listOf(
            OnBoardingItem(R.drawable.onboarding1, "Welcome", "Scan and extract text from images."),
            OnBoardingItem(R.drawable.onboarding2, "Powerful OCR", "High-quality text recognition powered by ML models."),
            OnBoardingItem(R.drawable.onboarding3, "Get Started", "Start scanning and saving your text now.")
        )

        adapter = OnBoardingAdapter(items)
        viewPager.adapter = adapter

        // Attach tab indicator
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Next button logic
        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < adapter.itemCount) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                markOnboardingComplete()
                openMainActivity()
            }
        }

        // Skip button logic
        btnSkip.setOnClickListener {
            markOnboardingComplete()
            openMainActivity()
        }

        // Change button text on last page
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                btnNext.text = if (position == adapter.itemCount - 1) "Start" else "Next"
            }
        })
    }

    private fun markOnboardingComplete() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETE, true).apply()
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
