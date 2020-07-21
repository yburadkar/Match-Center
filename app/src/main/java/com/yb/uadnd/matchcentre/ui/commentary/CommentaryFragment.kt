package com.yb.uadnd.matchcentre.ui.commentary


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_commentary.*

class CommentaryFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: CommentaryAdapter
    private lateinit var mRes: SimpleIdlingResource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = activity?.run { ViewModelProviders.of(this)
                .get(MainActivityViewModel::class.java) } ?: throw Exception("Invalid Activity")
        mRes = MyApp.getIdlingResource()
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
        mViewModel.getComments().observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            if(it.isNotEmpty()) mRes.setIdleState(true)
        })
    }

    private fun initRecyclerView() {
        commRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = CommentaryAdapter()
        commRecyclerView.adapter = mAdapter
        commRecyclerView.hasFixedSize()
    }
}
