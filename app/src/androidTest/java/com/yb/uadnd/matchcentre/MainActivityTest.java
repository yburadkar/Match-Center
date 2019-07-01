package com.yb.uadnd.matchcentre;

import android.content.Context;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.yb.uadnd.matchcentre.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private SimpleIdlingResource mResource;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class,true, false );

    @Before
    public void setUpTest(){
        mResource = MyApp.Companion.getIdlingResource();
        IdlingRegistry.getInstance().register(mResource);
    }

    @After
    public void cleanUpTest(){
        IdlingRegistry.getInstance().unregister(mResource);
    }

    @Test
    public void checkAllCommentaryEntriesLoaded(){
        MainActivity activity = activityTestRule.launchActivity(null);
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        onView(withText("OFFSIDE"))
                .check(matches(isDisplayed()));

        onView(withId(R.id.commRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition(86));

        onView(withText("LINEUP")).check(matches(isDisplayed()));
    }

}
