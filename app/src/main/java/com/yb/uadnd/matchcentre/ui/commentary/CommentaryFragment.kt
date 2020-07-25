package com.yb.uadnd.matchcentre.ui.commentary


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
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_commentary.*

class CommentaryFragment : Fragment() {

    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((requireActivity().application as MyApp).matchRepo, (requireActivity().application as MyApp).db)
    }
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }
    private lateinit var commentaryAdapter: CommentaryAdapter
    private lateinit var idlingResource: SimpleIdlingResource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idlingResource = MyApp.getIdlingResource()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_commentary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        watchCommentaryFromDb()
    }

    private fun watchCommentaryFromDb() {
        viewModel.getComments().observe(viewLifecycleOwner, Observer {
            commentaryAdapter.updateList(it)
            if(it.isNotEmpty()) idlingResource.setIdleState(true)
        })
    }

    private fun initRecyclerView() {
        commRecyclerView.layoutManager = LinearLayoutManager(context)
        commentaryAdapter = CommentaryAdapter()
        commRecyclerView.adapter = commentaryAdapter
        commRecyclerView.hasFixedSize()
    }
}
