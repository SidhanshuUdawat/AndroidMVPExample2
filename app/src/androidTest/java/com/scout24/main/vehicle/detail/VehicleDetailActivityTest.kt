package com.scout24.main.vehicle.detail

import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
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
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.scout24.R
import com.scout24.Scout24Application
import com.scout24.datasets.Images
import com.scout24.datasets.Seller
import com.scout24.datasets.Vehicle
import com.scout24.main.vehicle.VehicleActivity
import com.scout24.model.RealmImage
import com.scout24.model.RealmSeller
import com.scout24.model.RealmVehicle
import com.scout24.realm.RealmManager
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import io.realm.RealmList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

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
    var vehicleDetailActivityRule = IntentsTestRule(VehicleDetailActivity::class.java, false, false)

    private lateinit var realmManager: RealmManager
    private val context = getInstrumentation().targetContext.applicationContext

    @Before
    fun setUp() {
        RESTMockServer.reset()
        //Delete all media from realm
        realmManager = (context as Scout24Application).getApplicationComponent().getRealmManager()
        realmManager.clearData()
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

    @Test
    fun it_shows_detail_screen_with_vehicle_details_on_the_screen_read_from_realm() {
        val vehicle = createVehicle()
        storeVehicles(vehicle)

        val intent = VehicleDetailActivity.getIntent(context, 5000)
        vehicleDetailActivityRule.launchActivity(intent)

        Thread.sleep(100)

        onView(withId(R.id.vehicleTitle)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehiclePrice)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleFuelType)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleMileage)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vehicleDescription)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.vehicleTitle)).check(matches(withText("BMW 6 series")))
        onView(withId(R.id.vehiclePrice)).check(matches(withText("€ 300000")))
        onView(withId(R.id.vehicleFuelType)).check(matches(withText("Gasoline")))
        onView(withId(R.id.vehicleMileage)).check(matches(withText("2000")))
        onView(withId(R.id.vehicleDescription)).check(matches(withText("some description")))

    }


    private fun storeVehicles(vehicle: Vehicle) {
        val realmVehicleToStore = createRealmVehicle(vehicle)
        val realm = realmManager.realm
        try {
            realm.executeTransaction({
                val realmVehicle = realm.where(RealmVehicle::class.java).findFirst()
                realmVehicle.let {
                    realm.copyToRealmOrUpdate(realmVehicleToStore)
                }
            })
        } finally {
            realmManager.closeRealm(realm)
        }
    }

    private fun createRealmVehicle(vehicle: Vehicle): RealmVehicle {
        val modelline = vehicle.modelline ?: ""
        val firstRegistration = vehicle.firstRegistration ?: ""
        val seller = if (vehicle.seller != null) {
            RealmSeller(vehicle.seller!!.type, vehicle.seller!!.phone, vehicle.seller!!.city)
        } else {
            RealmSeller()
        }

        val images = if (vehicle.images != null && vehicle!!.images!!.isNotEmpty()) {
            val images = RealmList<RealmImage>()
            for (image in vehicle.images!!) {
                images.add(RealmImage(image.url))
            }
            images
        } else {
            RealmList()
        }
        return RealmVehicle(vehicle.id, vehicle.make, vehicle.model,
                modelline, vehicle.price, firstRegistration, vehicle.mileage,
                vehicle.fuel, seller, images, vehicle.description)
    }

    private fun createVehicle(): Vehicle {
        val seller = Seller("Single", "1234566778", "Berlin")
        val images = ArrayList<Images>()
        return Vehicle(5000, "BMW", "6 series",
                "SomeModel", 300000, "",
                2000, "Gasoline", seller, images, "some description")
    }
}