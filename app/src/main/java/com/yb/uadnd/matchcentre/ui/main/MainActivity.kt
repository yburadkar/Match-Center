package com.yb.uadnd.matchcentre.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var pagerAdapter: MatchPagerAdapter
    private var matchId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //matchId hardcoded for this exercise, normally would be received in an intent
        matchId = 987597

        intiViewModel()
        pagerAdapter = MatchPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun intiViewModel() {
        mViewModel = ViewModelProviders.of(this,
                MainActivityViewModelFactory(application, matchId))
                .get(MainActivityViewModel::class.java)
        val matchInfoObserver = Observer<MatchInfo> {
            if(it != null) {
                Timber.i(it.toString())
                val scoreText = "${it.homeScore} - ${it.awayScore}"
                score.text = scoreText
                home_team.text = it.homeTeamName
                away_team.text = it.awayTeamName
                competition.text = it.competition
                home_logo.setImageResource(Utils.getTeamLogo(it.homeTeamId))
                away_logo.setImageResource(Utils.getTeamLogo(it.awayTeamId))
            }
        }
        mViewModel.getMatchInfo().observe(this, matchInfoObserver)
    }

}
