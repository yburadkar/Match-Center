package com.yb.uadnd.matchcentre.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.databinding.FragmentEventsBinding
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((requireActivity().application as App).matchRepo)
    }
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.events?.let {
                eventsAdapter.updateEventList(it)
            }
        })
    }

    private fun initRecyclerView() {
        eventsAdapter = EventsAdapter()
        with(binding.eventsRecyclerView) {
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
