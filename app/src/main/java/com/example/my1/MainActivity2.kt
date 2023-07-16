package com.example.my1

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayout

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var tabLayout = findViewById<TabLayout>(R.id.tab)
        tabLayout.setTabTextColors(Color.RED, Color.BLACK);
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewAdapter(supportFragmentManager)
        viewPager.setAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
    }
}