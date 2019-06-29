package com.yb.uadnd.matchcentre.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MatchPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CommentaryFragment()
            1 -> EventsFragment()
            2 -> LineUpFragment()
            else -> return Fragment()

        }
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "Commentary"
            1 -> "Events"
            2 -> "Line Ups"
            3 -> "Stats"
            else -> "$position"
        }
    }
}