package com.yb.uadnd.matchcentre.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.TeamStat
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment() {

    private var mStats = ArrayList<TeamStat>()
    private lateinit var mViewModel: MainActivityViewModel
    private var mAdapter = StatsAdapter(mStats)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        mViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        val observer = Observer<Match>() {
            val stats = it.data?.getTeamStats()
            mStats.clear()
            mStats.addAll(stats!!)
            mAdapter.notifyDataSetChanged()
            home_team.text = it.data.homeTeam?.name
            away_team.text = it.data.awayTeam?.name
            Log.i("Number of stats: ", stats.size.toString())
        }
        mViewModel.getMatch().observe(activity!!, observer)
    }

    private fun initRecyclerView() {
        stats_list.layoutManager = LinearLayoutManager(activity)
        stats_list.adapter = mAdapter
        stats_list.hasFixedSize()
    }
}
