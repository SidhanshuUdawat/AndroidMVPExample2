package com.scout24.main.vehicle.detail

import com.scout24.datasets.Images
import com.scout24.datasets.Seller
import com.scout24.datasets.Vehicle
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Observable
import java.util.*

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleDetailPresenterTest {

    private lateinit var view: VehicleDetailMvp.View
    private lateinit var interactor: VehicleDetailMvp.Interactor
    private lateinit var presenter: VehicleDetailPresenter

    @Before
    fun setUp() {
        view = mock(VehicleDetailMvp.View::class.java)
        interactor = mock(VehicleDetailMvp.Interactor::class.java)
        presenter = VehicleDetailPresenter(view, interactor)
    }

    @Test
    fun it_gets_vehicle_data_when_init() {
        `when`(interactor.getVehicle()).thenReturn(Observable.empty())
        presenter.init()
        verify(interactor).getVehicle()
        verifyNoMoreInteractions(interactor)
    }

    @Test
    fun it_sets_title_price_fuel_type_mileage_and_description_when_fetching_vehicle_successful() {
        val vehicle = createVehicle()

        presenter.onFetchingVehicleDataSuccess(vehicle)
        verify(view).setTitle(vehicle.make + " " + vehicle.model)
        verify(view).setPrice("€ " + vehicle.price.toString())
        verify(view).setFuelType(vehicle.fuel)
        verify(view).setMileage(vehicle.mileage.toString())
        verify(view).setDescription(vehicle.description)
    }

    private fun createVehicle(): Vehicle {
        val seller = Seller("Single", "1234566778", "Berlin")
        val images = ArrayList<Images>()
        return Vehicle(1, "BMW", "6 series",
                "SomeModel", 300000, "",
                2000, "Gasoline", seller, images, "some description")
    }

}