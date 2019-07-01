package com.yb.uadnd.matchcentre.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_commentary.*

class CommentaryFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: CommentaryAdpater
    private var mCommentary: ArrayList<Comment> = ArrayList()
    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = activity?.run { ViewModelProviders.of(this)
                .get(MainActivityViewModel::class.java) } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commentary, container, false)
        mContext = context
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        watchCommentaryFromDb()
    }

    private fun watchCommentaryFromDb() {
        val observer = Observer<List<Comment>> {
            mCommentary.clear()
            mCommentary.addAll(it)
            mAdapter.notifyDataSetChanged()
        }
        mViewModel.getComments().observe(activity as LifecycleOwner, observer )
    }

    private fun initRecyclerView() {
        commRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = CommentaryAdpater(mCommentary, mContext)
        commRecyclerView.adapter = mAdapter
        commRecyclerView.hasFixedSize()
    }
}
