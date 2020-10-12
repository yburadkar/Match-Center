package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.helpers.Resource
import com.yb.uadnd.matchcentre.helpers.ResourceStatus
import com.yb.uadnd.matchcentre.databinding.ActivityMainBinding
import com.yb.uadnd.matchcentre.di.ViewModelFactory
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.helpers.showSnackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        observeViewModel()
    }

    private fun setUpViews() {
        initViewPager()
        binding.rightButton.setOnClickListener {
            viewModel.loadNextMatch()
            initViewPager()
            observeViewModel()
        }

        binding.leftButton.setOnClickListener {
            viewModel.loadPrevMatch()
            initViewPager()
            observeViewModel()
        }
    }

    private fun inject() {
        (application as App).appComponent.inject(this)
    }

    private fun initViewPager() {
        val pagerAdapter = MatchPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.matchTabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun observeViewModel() {
        viewModel.matchCommentary.observe(this) {
            showUserMessages(it.status)
            renderViewState(it)
        }
    }

    private fun renderViewState(resource: Resource<MatchCommentary>) {
        with(binding) {
            val homeScore = resource.data?.homeScore ?: ""
            val awayScore = resource.data?.awayScore ?: ""
            val scoreText = "$homeScore - $awayScore"
            score.text = scoreText
            homeTeam.text = resource.data?.homeTeamName
            awayTeam.text = resource.data?.awayTeamName
            competition.text = resource.data?.competition
            Picasso.get().load(resource.data?.homeTeamImageUrl).into(homeLogo)
            Picasso.get().load(resource.data?.awayTeamImageUrl).into(awayLogo)
        }
    }

    private fun showUserMessages(status: ResourceStatus) {
        with(binding) {
            if (status == ResourceStatus.ERROR) {
                root.showSnackbar(getString(R.string.loading_error_message))
            }
        }
    }

}