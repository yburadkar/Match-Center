package com.yb.uadnd.matchcentre.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yb.uadnd.matchcentre.data.remote.models.ApiMatch
import com.yb.uadnd.matchcentre.domain.models.Match
import com.yb.uadnd.matchcentre.domain.repos.MatchCommentaryRepository
import com.yb.uadnd.matchcentre.domain.repos.MatchRepository
import com.yb.uadnd.matchcentre.helpers.Resource
import com.yb.uadnd.matchcentre.helpers.ResourceStatus
import com.yb.uadnd.matchcentre.helpers.SimpleIdlingResource
import com.yb.uadnd.matchcentre.helpers.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private val matchRepo: MatchRepository = mockk()
    private val matchCommRepo: MatchCommentaryRepository = mockk()
    private val io: Scheduler = Schedulers.trampoline()
    private val ui: Scheduler = Schedulers.trampoline()
    private val idlingRes = SimpleIdlingResource
    private lateinit var viewModel: MainViewModel

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        every { matchRepo.getMatchList() } returns (listOf(24122, 34342))
        viewModel = MainViewModel(matchRepo, matchCommRepo, io, ui, idlingRes)
    }

    @Test
    fun `fetchMatchData given success response, verify that live data is a success resource`() {
        //Given
        every { matchRepo.fetchMatch(any()) } returns (Single.just(ApiMatch()))

        //When
        viewModel.fetchMatchData()

        //Then
        val value = viewModel.match.getOrAwaitValue()
        assertThat(value.status, `is`(ResourceStatus.SUCCESS))
    }

    @Test
    fun `fetchMatchData, verify that first live data emission is a loading resource`() {
        //Given
        every { matchRepo.fetchMatch(any()) } returns (Single.just(ApiMatch()))
        val emissions = mutableListOf<Resource<Match>>()
        val observer = Observer<Resource<Match>> { emissions.add(it) }
        viewModel.match.observeForever(observer)

        //When
        viewModel.fetchMatchData()

        //Then
        val value = viewModel.match.getOrAwaitValue()
        assertThat(emissions.first().status, `is`(ResourceStatus.LOADING))
        viewModel.match.removeObserver(observer)
    }

    @Test
    fun `fetchMatchData given error response, verify that live data is a error resource`() {
        //Given
        every { matchRepo.fetchMatch(any()) } returns (Single.error(Throwable()))

        //When
        viewModel.fetchMatchData()

        //Then
        val value = viewModel.match.getOrAwaitValue()
        assertThat(value.status, `is`(ResourceStatus.ERROR))
    }

}