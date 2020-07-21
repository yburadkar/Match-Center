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
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_line_up.*

class LineUpFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var homeAdapter: LineUpAdapter
    private lateinit var awayAdapter: LineUpAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_line_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerViews()
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.let {
                homeAdapter.updateList(it.homeTeam?.players ?: emptyList())
                home_team.text = it.homeTeam?.name
                awayAdapter.updateList(it.awayTeam?.players ?: emptyList())
                away_team.text = it.awayTeam?.name
            }
        })
    }

    private fun initRecylerViews() {
        homeAdapter = LineUpAdapter()
        awayAdapter = LineUpAdapter()
        home_list.layoutManager = LinearLayoutManager(context)
        home_list.adapter = homeAdapter
        away_list.layoutManager = LinearLayoutManager(context)
        away_list.adapter = awayAdapter
    }

}
