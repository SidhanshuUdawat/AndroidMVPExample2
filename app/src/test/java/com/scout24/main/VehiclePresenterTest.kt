package com.scout24.main

import com.scout24.datasets.Images
import com.scout24.datasets.Seller
import com.scout24.datasets.Vehicle
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import rx.Observable

/**
 * Created by Sid on 15/06/2018.
 */

class VehiclePresenterTest {

    private lateinit var view: VehicleMvp.View
    private lateinit var interactor: VehicleMvp.Interactor
    private lateinit var presenter: VehiclePresenter

    @Before
    fun setUp() {
        view = mock(VehicleMvp.View::class.java)
        interactor = mock(VehicleMvp.Interactor::class.java)
        presenter = VehiclePresenter(view, interactor)
    }

    @Test
    fun it_fetches_vehicle_list_when_init() {
        `when`(interactor.getVehicles()).thenReturn(Observable.empty())

        presenter.init()
        verify(view).showProgress()
        verify(interactor).getVehicles()
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun it_shows_error_when_fetching_vehicle_failed() {
        presenter.onFetchingVehiclesFailed()

        verify(view).hideProgress()
        verify(view).showError()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_shows_error_when_api_returned_empty_list_of_vehicles() {
        val emptyList = ArrayList<Vehicle>()
        presenter.onFetchingVehiclesSuccess(emptyList)

        verify(view).hideProgress()
        verify(view).showError()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_updates_list_with_fetched_vehicle_list() {
        val vehicle = createVehicle()
        val vehicleList = ArrayList<Vehicle>()
        vehicleList.add(vehicle)
        presenter.onFetchingVehiclesSuccess(vehicleList)

        verify(view).hideProgress()
        verify(view).updateList(ArgumentMatchers.anyList())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_shows_progress_hide_error_and_fetches_vehicle_when_retry() {
        `when`(interactor.getVehicles()).thenReturn(Observable.empty())

        presenter.onRetryClicked()
        verify(view).showProgress()
        verify(view).hideError()
        verify(interactor).getVehicles()
        verifyNoMoreInteractions(view, interactor)
    }


    private fun createVehicle(): Vehicle {
        val seller = Seller("Single", "1234566778", "Berlin")
        val images = ArrayList<Images>()
        return Vehicle(1, "BMW", "6 series",
                "SomeModel", 300000, "",
                2000, "Gasoline", seller, images, "some description")
    }


}