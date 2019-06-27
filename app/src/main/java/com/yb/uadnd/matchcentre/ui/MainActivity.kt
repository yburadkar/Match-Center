package com.yb.uadnd.matchcentre.ui

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var pagerAdapter: MatchPagerAdapter
    private var matchId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hardcoded for this exercise, normally matchId will be received as an intent extra
        matchId = 987597
        intiViewModel()
        pagerAdapter = MatchPagerAdapter(supportFragmentManager);
        viewPager.adapter = pagerAdapter
        matchTabLayout.setupWithViewPager(viewPager)
    }

    private fun intiViewModel() {

    }

}
