package com.nyt.mostpopular.mostviewed

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nyt.MockServerTest
import com.nyt.mostpopular.R
import com.nyt.mostpopular.mostviewed.adapter.MostViewedVH
import com.nyt.network.ApiWrapper
import com.nyt.utils.RecyclerViewMatcher
import com.nyt.utils.hasItemCount
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection


/**
 * Created by dastaniqbal on 12/07/2019.
 * 12/07/2019 11:18
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MostViewedFragmentTest : MockServerTest() {
    private val TAG = this::class.java.simpleName
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MostViewedActivity::class.java, true, false)

    @get:Rule
    var executorRule = CountingTaskExecutorRule()

    private fun getString(id: Int) = activityRule.activity.getString(id)
    private fun withRecyclerViewItemId(pos: Int, id: Int) = RecyclerViewMatcher(R.id.rv).atPositionOnView(pos, id)

    @Before
    override fun setUp() {
        super.setUp()
        ApiWrapper.SERVICE_URL = getMockUrl()
        activityRule.launchActivity(null)
    }

    @Test
    fun mostViewedDay1() {
        mockHttpResponse("1Day.json", HttpURLConnection.HTTP_OK)

        onView(withId(R.id.rv)).check(matches(isDisplayed()))
        onView(withId(R.id.pb)).check(matches(isDisplayed()))

        Thread.sleep(3000)

        onView(withId(R.id.pb)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rv)).check(matches((hasItemCount(20))))

        onView(withRecyclerViewItemId(0, R.id.tv_headline)).check(
            matches(withText("Woman Required to Cover Up on American Airlines Flight Says Race Was a Factor"))
        ).check(matches(isDisplayed()))

        onView(withRecyclerViewItemId(0, R.id.tv_byline)).check(
            matches(withText("By NEIL VIGDOR"))
        ).check(matches(isDisplayed()))

        onView(withRecyclerViewItemId(0, R.id.tv_pubtime)).check(
            matches(withText("2019-07-10"))
        ).check(matches(isDisplayed()))

        onView(withId(R.id.rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MostViewedVH>(3, ViewActions.click()))
    }

    @Test
    fun whenNoData() {
        mockHttpResponse("1Day.json", HttpURLConnection.HTTP_BAD_REQUEST)

        Thread.sleep(3000)

        onView(withId(R.id.pb)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rv)).check(matches(not(isDisplayed())))

        onView(withId(R.id.btn_retry)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(isDisplayed()))

        onView(withId(R.id.textView)).check(
            matches(withText(Matchers.containsString(getString(R.string.no_data_found))))
        )
    }
}