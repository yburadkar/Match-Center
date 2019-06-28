package com.yb.uadnd.matchcentre.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

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

        pagerAdapter = MatchPagerAdapter(supportFragmentManager);
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun intiViewModel() {
        mViewModel = ViewModelProviders.of(this,
                MainActivityViewModelFactory(matchId))
                .get(MainActivityViewModel::class.java)
        val matchObserver = Observer<Commentary> {
            val data: Commentary.Data? = it?.data
            val scoreText: String = data?.homeScore.toString() + " - " + data?.awayScore
            score.text = scoreText
            home_team.text = data?.homeTeamName
            away_team.text = data?.awayTeamName
            competition.text = data?.competition
            home_logo.setImageDrawable(getTeamLogo(data?.homeTeamId))
            away_logo.setImageDrawable(getTeamLogo(data?.awayTeamId))
        }
        mViewModel.getCommentary().observe(this, matchObserver)
    }

    private fun getTeamLogo(teamId: String?): Drawable? {
        return when(teamId) {
            "1" -> getDrawable(R.drawable.manunited)
            "13" -> getDrawable(R.drawable.leicestercity)
            else -> getDrawable(R.drawable.ic_launcher_foreground)
        }
    }

    override fun onResume() {
        super.onResume()
    }

}
