package com.example.pomodoro.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pomodoro.R
import com.example.pomodoro.view.adapters.PageAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        supportActionBar?.elevation = 0.0f
        view_pager.adapter = PageAdapter(this)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = PageAdapter.PAGES[position].second
        }.attach()
    }
}