package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pagerAdapter: MatchPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intiViewModel()
        initViewPager()
        observeViewModel()
        competition.setOnClickListener {
            viewModel.getMatchInfo().removeObservers(this)
            viewModel.loadMatch(viewModel.getNextMatchId())
            initViewPager()
            observeViewModel()
        }
    }

    private fun initViewPager() {
        pagerAdapter = MatchPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun intiViewModel() {
        val app = (application as MyApp)
        val factory = MainActivityViewModelFactory(app.matchRepo, app.db)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        viewModel.loadMatch(viewModel.getNextMatchId())
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
