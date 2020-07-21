package com.yb.uadnd.matchcentre.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yb.uadnd.matchcentre.ui.commentary.CommentaryFragment
import com.yb.uadnd.matchcentre.ui.events.EventsFragment
import com.yb.uadnd.matchcentre.ui.lineups.LineUpFragment
import com.yb.uadnd.matchcentre.ui.stats.StatsFragment

class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> CommentaryFragment()
        1 -> EventsFragment()
        2 -> LineUpFragment()
        3 -> StatsFragment()
        else -> Fragment()
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Commentary"
        1 -> "Events"
        2 -> "Line Ups"
        3 -> "Stats"
        else -> "$position"
    }
}