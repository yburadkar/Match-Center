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
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.databinding.FragmentCommentaryBinding
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory

class CommentaryFragment : Fragment() {

    private var _binding: FragmentCommentaryBinding? = null
    private val binding get() = _binding!!
    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((requireActivity().application as MyApp).matchRepo)
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
        _binding = FragmentCommentaryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getComments().observe(viewLifecycleOwner, Observer {
            commentaryAdapter.updateList(it)
            if(it.isNotEmpty()) idlingResource.setIdleState(true)
        })
    }

    private fun initRecyclerView() {
        commentaryAdapter = CommentaryAdapter()
        with(binding.commRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = commentaryAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
