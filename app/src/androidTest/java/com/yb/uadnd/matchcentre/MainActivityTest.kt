package com.yb.uadnd.matchcentre

import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import com.yb.uadnd.matchcentre.ui.main.MainActivity

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mResource = SimpleIdlingResource

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(
            MainActivity::class.java, true, false)

    @Before
    fun setUpTest() {
        IdlingRegistry.getInstance().register(mResource)
    }

    @After
    fun cleanUpTest() {
        IdlingRegistry.getInstance().unregister(mResource)
    }

    @Test
    fun checkAllCommentaryEntriesLoaded() {
        val activity = activityTestRule.launchActivity(null)
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        onView(withText("OFFSIDE"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.commRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(86))

        onView(withText("LINEUP")).check(matches(isDisplayed()))
    }

}
