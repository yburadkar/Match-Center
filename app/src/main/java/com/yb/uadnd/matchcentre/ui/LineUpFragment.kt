package com.yb.uadnd.matchcentre.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_line_up.*

class LineUpFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel
    private var mHomePlayers = ArrayList<Match.Data.Team.Player>()
    private var mAwayPlayers = ArrayList<Match.Data.Team.Player>()
    private val mHomeAdapter = LineUpAdapter(mHomePlayers)
    private val mAwayAdapter = LineUpAdapter(mAwayPlayers)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_line_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerViews()
        mViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        val observer = Observer<Match>() {
            val homeTeam = it.data?.homeTeam
            home_team.text = homeTeam?.name
            mHomePlayers.clear()
            mHomePlayers.addAll(homeTeam?.players!!)
            mHomeAdapter.notifyDataSetChanged()
            val awayTeam = it.data.awayTeam
            away_team.text = awayTeam?.name
            mAwayPlayers.clear()
            mAwayPlayers.addAll(awayTeam?.players!!)
            mAwayAdapter.notifyDataSetChanged()
        }
        mViewModel.getMatch().observe(activity!!, observer)
    }

    private fun initRecylerViews() {
        home_list.layoutManager = LinearLayoutManager(activity)
        home_list.adapter = mHomeAdapter
        away_list.layoutManager = LinearLayoutManager(activity)
        away_list.adapter = mAwayAdapter
    }

}
