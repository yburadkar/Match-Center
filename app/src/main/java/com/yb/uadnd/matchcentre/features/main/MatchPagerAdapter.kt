package com.yb.uadnd.matchcentre.features.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.features.commentary.CommentaryFragment
import com.yb.uadnd.matchcentre.features.events.EventsFragment
import com.yb.uadnd.matchcentre.features.lineups.LineUpFragment
import com.yb.uadnd.matchcentre.features.stats.StatsFragment

class MatchPagerAdapter(
    private val context: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> CommentaryFragment.newInstance()
        1 -> EventsFragment.newInstance()
        2 -> LineUpFragment.newInstance()
        3 -> StatsFragment.newInstance()
        else -> throw Exception("Invalid tab position")
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> context.getString(R.string.commentary_tab_title)
        1 -> context.getString(R.string.events_tab_title)
        2 -> context.getString(R.string.lineups_tab_title)
        3 -> context.getString(R.string.stats_tab_title)
        else -> "$position"
    }
}