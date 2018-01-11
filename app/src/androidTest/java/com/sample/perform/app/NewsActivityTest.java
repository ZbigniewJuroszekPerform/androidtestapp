package com.sample.perform.app;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.perform.app.ui.news.NewsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class NewsActivityTest  {

    private static final int ITEM_POSITION = 2;

    private static String headline = "Paul sparks fireworks after win over Trail Blazers, Clippers stun Warriors";

    @Rule
    public ActivityTestRule<NewsActivity> activityTestRule = new ActivityTestRule<>(NewsActivity.class);


    @Test
    public void scrollToItemBelowFold_checkItsText() {
        onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_POSITION, click()));
        onView(withText(headline)).check(matches(isDisplayed()));
    }

}
