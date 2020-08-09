package com.yb.uadnd.matchcentre.ui.stats


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.databinding.FragmentStatsBinding
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory

class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val  binding get() = _binding!!
    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((requireActivity().application as App).matchRepo)
    }
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private var statsAdapter = StatsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.let {
                statsAdapter.updateList(it.getTeamStats())
                with(binding) {
                    homeTeam.text = it.homeTeam?.name
                    awayTeam.text = it.awayTeam?.name
                }
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.statsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = statsAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
