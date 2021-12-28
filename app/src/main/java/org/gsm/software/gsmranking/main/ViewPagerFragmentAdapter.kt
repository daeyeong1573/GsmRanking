package org.gsm.software.gsmranking.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.gsm.software.gsmranking.allrangking.AllRankingFragment
import org.gsm.software.gsmranking.generation.GenerationRankingFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf<Fragment>(AllRankingFragment(),GenerationRankingFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}