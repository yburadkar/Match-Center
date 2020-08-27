package com.yb.uadnd.matchcentre.ui.stats


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.databinding.FragmentStatsBinding
import com.yb.uadnd.matchcentre.ui.main.MainActivityViewModel
import com.yb.uadnd.matchcentre.ui.main.MainActivityViewModelFactory
import javax.inject.Inject

class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private var statsAdapter = StatsAdapter()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
        binding.swipeRefresh.setOnRefreshListener { viewModel.reloadMatch() }
    }

    private fun observeViewModel() {
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { matchRes ->
            val match = matchRes.data
            match?.data?.let {
                statsAdapter.submitList(it.getTeamStats())
                with(binding) {
                    homeTeam.text = it.homeTeam?.name
                    awayTeam.text = it.awayTeam?.name
                }
            }
            binding.swipeRefresh.isRefreshing = matchRes.isLoading
            if(matchRes.isError) {
                Snackbar.make(requireView(), "Failed to load data", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.statsRv) {
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
