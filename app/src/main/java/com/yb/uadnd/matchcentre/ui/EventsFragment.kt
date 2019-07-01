package com.yb.uadnd.matchcentre.ui


import android.content.Context
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
import com.yb.uadnd.matchcentre.model.Match.Data.Event
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: EventsAdapter
    private var mEvents = ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        val observer = Observer<Match>(){
            mEvents.clear()
            mEvents.addAll(it.data?.events!!)
            mAdapter.notifyDataSetChanged()
        }
        mViewModel.getMatch().observe(activity!!, observer)
    }

    private fun initRecyclerView() {
        events_recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = EventsAdapter(mEvents, activity as Context)
        events_recyclerView.adapter = mAdapter

    }

}
