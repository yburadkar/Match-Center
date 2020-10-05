package com.yb.uadnd.matchcentre.di

import com.yb.uadnd.matchcentre.ui.commentary.CommentaryFragment
import com.yb.uadnd.matchcentre.ui.events.EventsFragment
import com.yb.uadnd.matchcentre.ui.lineups.LineUpFragment
import com.yb.uadnd.matchcentre.ui.main.MainActivity
import com.yb.uadnd.matchcentre.ui.stats.StatsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DbModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CommentaryFragment)

    fun inject(fragment: EventsFragment)

    fun inject(fragment: LineUpFragment)

    fun inject(fragment: StatsFragment)

}