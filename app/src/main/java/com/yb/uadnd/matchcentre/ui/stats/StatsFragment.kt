package com.yb.uadnd.matchcentre.ui.stats


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment() {

    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((requireActivity().application as MyApp).matchRepo)
    }
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private var statsAdapter = StatsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.let {
                statsAdapter.updateList(it.getTeamStats())
                home_team.text = it.homeTeam?.name
                away_team.text = it.awayTeam?.name
            }
        })
    }

    private fun initRecyclerView() {
        stats_list.layoutManager = LinearLayoutManager(context)
        stats_list.adapter = statsAdapter
        stats_list.hasFixedSize()
    }
}
