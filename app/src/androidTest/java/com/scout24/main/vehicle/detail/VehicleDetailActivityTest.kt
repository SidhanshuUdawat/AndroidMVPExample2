package com.scout24.main.vehicle.detail

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.scout24.R
import com.scout24.main.vehicle.VehicleActivity
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Sid on 15/06/2018.
 */

@RunWith(AndroidJUnit4::class)
class VehicleDetailActivityTest {

    private val intent = Intent()

    @Rule
    @JvmField
    var vehicleActivityRule = IntentsTestRule(VehicleActivity::class.java, false, false)

    @Rule
    @JvmField
    var vehicleDetailActivityRule = IntentsTestRule(VehicleActivity::class.java, false, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun it_shows_detail_screen_when_list_item_is_clicked() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        vehicleActivityRule.launchActivity(intent)
        Thread.sleep(100)
        onView(withId(R.id.vehicleRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        intended(hasComponent(hasClassName(VehicleDetailActivity::class.java.name)))
    }

    @Test
    fun it_shows_detail_screen_with_vehicle_details_on_the_screen() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("/")).thenReturnFile(200, "api/vehicles.json")
        vehicleActivityRule.launchActivity(intent)
        Thread.sleep(100)
        onView(withId(R.id.vehicleRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.vehicleTitle)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehiclePrice)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleFuelType)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleMileage)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleDescription)).check(matches(ViewMatchers.isDisplayed()))
    }
}