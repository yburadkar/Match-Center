package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

import com.yb.uadnd.matchcentre.App
import com.yb.uadnd.matchcentre.databinding.ActivityMainBinding
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadNextMatch()
        initViewPager()
        observeViewModel()

        binding.rightButton.setOnClickListener {
            viewModel.getMatchInfo().removeObservers(this)
            viewModel.loadNextMatch()
            initViewPager()
            observeViewModel()
        }

        binding.leftButton.setOnClickListener {
            viewModel.getMatchInfo().removeObservers(this)
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
        viewModel.getMatchInfo().observe(this, Observer {
            it?.run {
                val scoreText = "$homeScore - $awayScore"
                binding.score.text = scoreText
                binding.homeTeam.text = homeTeamName
                binding.awayTeam.text = awayTeamName
                binding.competition.text = this.competition
                Picasso.get().load(homeTeamImageUrl).into(binding.homeLogo)
                Picasso.get().load(awayTeamImageUrl).into(binding.awayLogo)
            }
        })
    }

}