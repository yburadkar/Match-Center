package com.yb.uadnd.matchcentre.ui.lineups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.databinding.FragmentLineUpBinding
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import javax.inject.Inject

class LineUpFragment : Fragment() {

    private var _binding: FragmentLineUpBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private lateinit var homeAdapter: LineUpAdapter
    private lateinit var awayAdapter: LineUpAdapter


    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() = (requireActivity().application as App).appComponent.inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLineUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.let {
                homeAdapter.submitList(it.homeTeam?.players ?: emptyList())
                awayAdapter.submitList(it.awayTeam?.players ?: emptyList())
                with(binding) {
                    homeTeam.text = it.homeTeam?.name
                    awayTeam.text = it.awayTeam?.name
                }
            }
        })
    }

    private fun initRecylerViews() {
        homeAdapter = LineUpAdapter()
        awayAdapter = LineUpAdapter()
        with(binding) {
            homeLineUp.layoutManager = LinearLayoutManager(context)
            homeLineUp.adapter = homeAdapter
            homeLineUp.setHasFixedSize(true)
            awayLineUp.layoutManager = LinearLayoutManager(context)
            awayLineUp.adapter = awayAdapter
            awayLineUp.setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
