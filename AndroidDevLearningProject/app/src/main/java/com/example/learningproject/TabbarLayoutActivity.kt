package com.example.learningproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout

class TabbarLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tabbar_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val contentContainer = findViewById<LinearLayout>(R.id.contentContainer)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> inflateView(R.layout.view_one)
                    1 -> inflateView(R.layout.view_two)
                    2 -> inflateView(R.layout.view_three)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Optionally handle tab unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Optionally handle tab reselection
            }
        })

        val customView = LayoutInflater.from(this).inflate(R.layout.custom_tab_item, null) as View
        tabLayout.getTabAt(0)?.customView = customView
        tabLayout.getTabAt(0)?.select()
        inflateView(R.layout.view_one)
    }

    private fun inflateView(layoutResId: Int) {
        val contentContainer = findViewById<LinearLayout>(R.id.contentContainer)
        contentContainer.removeAllViews()
        LayoutInflater.from(this).inflate(layoutResId, contentContainer, true)
    }
}
