package com.yb.uadnd.matchcentre.ui.events


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
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getMatch().observe(viewLifecycleOwner, Observer { match ->
            match.data?.events?.let {
                eventsAdapter.updateEventList(it)
            }
        })
    }

    private fun initRecyclerView() {
        events_recyclerView.layoutManager = LinearLayoutManager(context)
        eventsAdapter = EventsAdapter()
        events_recyclerView.adapter = eventsAdapter
    }

}
