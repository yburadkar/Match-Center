package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pagerAdapter: MatchPagerAdapter
    private var matchId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //matchId hardcoded for this sample app
        matchId = 987597

        intiViewModel()
        pagerAdapter = MatchPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun intiViewModel() {
        val app = (application as MyApp)
        val factory = MainActivityViewModelFactory(app.matchRepo, app.db)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        viewModel.setMatch(matchId)
        viewModel.getMatchInfo().observe(this, Observer {
            it?.run {
                val scoreText = "$homeScore - $awayScore"
                score.text = scoreText
                home_team.text = homeTeamName
                away_team.text = awayTeamName
                this@MainActivity.competition.text = this.competition
                home_logo.setImageResource(Utils.getTeamLogo(homeTeamId))
                away_logo.setImageResource(Utils.getTeamLogo(awayTeamId))
            }
        })
    }

}
