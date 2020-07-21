package com.yb.uadnd.matchcentre.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.TeamStat
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment() {

    private var stats = ArrayList<TeamStat>()
    private lateinit var viewModel: MainActivityViewModel
    private var statsAdapter = StatsAdapter(stats)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
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
