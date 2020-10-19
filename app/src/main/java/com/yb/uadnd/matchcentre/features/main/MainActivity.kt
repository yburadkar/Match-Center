package com.yb.uadnd.matchcentre.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.databinding.ActivityMainBinding
import com.yb.uadnd.matchcentre.di.ViewModelFactory
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.helpers.Resource
import com.yb.uadnd.matchcentre.helpers.ResourceStatus
import com.yb.uadnd.matchcentre.helpers.showSnackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        observeViewModel()
        if(savedInstanceState == null) viewModel.loadNextMatch()
    }

    private fun setUpViews() {
        val pagerAdapter = MatchPagerAdapter(this, supportFragmentManager)
        with (binding) {
            viewPager.adapter = pagerAdapter
            matchTabLayout.setupWithViewPager(binding.viewPager)
            rightButton.setOnClickListener { viewModel.loadNextMatch() }
            leftButton.setOnClickListener { viewModel.loadPrevMatch() }
        }
    }

    private fun inject() {
        (application as App).appComponent.inject(this)
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