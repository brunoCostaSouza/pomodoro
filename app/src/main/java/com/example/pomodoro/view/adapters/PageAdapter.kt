package com.example.pomodoro.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pomodoro.view.fragments.FragmentHistory
import com.example.pomodoro.view.fragments.FragmentNew

class PageAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    companion object {
        val PAGES: List<Pair<Fragment, String>> = listOf(
            Pair(FragmentNew.newInstance(), "NEW"),
            Pair(FragmentHistory.newInstance(), "HISTORY")
        )
    }

    override fun getItemCount(): Int {
        return PAGES.size
    }

    override fun createFragment(position: Int): Fragment {
        return PAGES[position].first
    }
}
