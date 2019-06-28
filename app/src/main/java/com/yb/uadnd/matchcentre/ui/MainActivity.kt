package com.yb.uadnd.matchcentre.ui

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match
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
    }

}
