package com.scout24.main

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.scout24.R
import com.scout24.main.vehicle.VehicleActivity
import com.scout24.util.RecyclerViewItemCountAssertion
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Sid on 15/06/2018.
 */

@RunWith(AndroidJUnit4::class)
class VehicleActivityTest {

    private val intent = Intent()

    @Rule
    @JvmField
    var activityTestRule = IntentsTestRule(VehicleActivity::class.java, false, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun it_hides_spinner_when_vehicles_are_fetched() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        activityTestRule.launchActivity(intent)
        onView(withId((R.id.progressView))).check(matches(not(isDisplayed())))
    }

    @Test
    fun it_shows_error_screen_when_response_is_empty() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnEmpty(200)
        activityTestRule.launchActivity(intent)
        onView(withId((R.id.errorView))).check(matches(isDisplayed()))
    }

    @Test
    fun it_checks_for_vehicles_item_count_when_response_is_fetched() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        activityTestRule.launchActivity(intent)
        Thread.sleep(100)
        onView(withId((R.id.vehicleRecyclerView))).check(RecyclerViewItemCountAssertion(6))
    }

    @Test
    fun given_error_screen_when_fetching_vehicles_failed_then_retry_and_show_list_of_vehicles() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnEmpty(200)

        activityTestRule.launchActivity(intent)
        onView(withId((R.id.errorView))).check(matches(isDisplayed()))

        RESTMockServer.reset()
        RESTMockServer.whenGET(pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        onView(withId((R.id.errorRetryBtn))).perform(click())
        onView(withId((R.id.vehicleRecyclerView))).check(RecyclerViewItemCountAssertion(6))
    }

    @Test
    fun given_error_screen_when_fetching_vehicles_failed_then_retry_and_show_error() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnEmpty(200)

        activityTestRule.launchActivity(intent)
        onView(withId((R.id.errorView))).check(matches(isDisplayed()))

        RESTMockServer.reset()
        RESTMockServer.whenGET(pathContains("/")).thenReturnEmpty(200)
        onView(withId((R.id.errorRetryBtn))).perform(click())
        onView(withId((R.id.errorView))).check(matches(isDisplayed()))
    }

    @Test
    fun it_shows_advert_on_4_th_position() {
        RESTMockServer.whenGET(pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        activityTestRule.launchActivity(intent)
        Thread.sleep(100)
        onView(withId((R.id.vehicleRecyclerView))).perform(scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(AllOf.allOf<View>(withId(R.id.vehicleRecyclerView), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).check(matches(isDisplayed()))
    }
}