package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModelFactory by lazy {
        MainActivityViewModelFactory((application as MyApp).matchRepo)
    }
    private val viewModel: MainActivityViewModel by viewModels{ viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadNextMatch()
        initViewPager()
        observeViewModel()

        rightButton.setOnClickListener {
            viewModel.getMatchInfo().removeObservers(this)
            viewModel.loadNextMatch()
            initViewPager()
            observeViewModel()
        }

        leftButton.setOnClickListener {
            viewModel.getMatchInfo().removeObservers(this)
            viewModel.loadPrevMatch()
            initViewPager()
            observeViewModel()
        }
    }

    private fun initViewPager() {
        val pagerAdapter = MatchPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun observeViewModel() {
        viewModel.getMatchInfo().observe(this, Observer {
            it?.run {
                val scoreText = "$homeScore - $awayScore"
                score.text = scoreText
                home_team.text = homeTeamName
                away_team.text = awayTeamName
                this@MainActivity.competition.text = this.competition
                Picasso.get().load(homeTeamImageUrl).into(home_logo)
                Picasso.get().load(awayTeamImageUrl).into(away_logo)
            }
        })
    }

}
