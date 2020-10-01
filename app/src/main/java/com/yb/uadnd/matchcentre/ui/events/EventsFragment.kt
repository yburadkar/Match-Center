package com.yb.uadnd.matchcentre.ui.events

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
import com.yb.uadnd.matchcentre.databinding.FragmentEventsBinding
import com.yb.uadnd.matchcentre.ui.main.MainActivityViewModel
import com.yb.uadnd.matchcentre.ui.main.MainActivityViewModelFactory
import com.yb.uadnd.matchcentre.ui.models.UiMatchEvent
import javax.inject.Inject

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private lateinit var eventsAdapter: EventsAdapter

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() = (requireActivity().application as App).appComponent.inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.reloadMatch()
        }
    }

    private fun observeViewModel() {
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { matchRes ->
            val match = matchRes.data
            match?.data?.events?.let { events ->
                eventsAdapter.submitList(events.map { UiMatchEvent.from(it) })
            }
            binding.swipeRefresh.isRefreshing = matchRes.isLoading
            if (matchRes.isError) {
                Snackbar.make(requireView(), "Failed to load data", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        eventsAdapter = EventsAdapter()
        with(binding.eventsRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = eventsAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
