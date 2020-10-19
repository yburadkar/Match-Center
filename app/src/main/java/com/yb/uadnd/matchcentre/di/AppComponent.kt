package com.yb.uadnd.matchcentre.di

import com.yb.uadnd.matchcentre.data.di.DbModule
import com.yb.uadnd.matchcentre.data.di.NetworkModule
import com.yb.uadnd.matchcentre.features.commentary.CommentaryFragment
import com.yb.uadnd.matchcentre.features.events.EventsFragment
import com.yb.uadnd.matchcentre.features.lineups.LineUpFragment
import com.yb.uadnd.matchcentre.features.main.MainActivity
import com.yb.uadnd.matchcentre.features.stats.StatsFragment
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