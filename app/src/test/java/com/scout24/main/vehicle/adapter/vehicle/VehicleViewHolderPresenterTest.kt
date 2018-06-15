package com.scout24.main.vehicle.adapter.vehicle

import com.scout24.R
import com.scout24.datasets.Images
import com.scout24.datasets.Seller
import com.scout24.datasets.Vehicle
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Sid on 15/06/2018.
 */
class VehicleViewHolderPresenterTest {

    private lateinit var view: VehicleViewHolderMvp.View
    private lateinit var presenter: VehicleViewHolderPresenter

    @Before
    fun setUp() {
        view = mock(VehicleViewHolderMvp.View::class.java)
        presenter = VehicleViewHolderPresenter(view)
    }

    @Test
    fun it_sets_title_price_fuel_type_and_fall_back_image_when_image_is_not_available() {
        val vehicleViewModel = createVehicleViewModel(createVehicle())
        presenter.init(vehicleViewModel)

        verify(view).setVehicleImage(R.drawable.no_image_available)
        verify(view).hideProgress()
        verify(view).setTitle(vehicleViewModel.vehicle.make + " " + vehicleViewModel.vehicle.model)
        verify(view).setPrice("€ " + vehicleViewModel.vehicle.price.toString())
        verify(view).setFuelType(vehicleViewModel.vehicle.fuel)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_hides_progress_when_image_loading_success() {
        presenter.onImageLoadingSuccess()
        verify(view).hideProgress()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_hides_progress_when_image_loading_failed() {
        presenter.onImageLoadingFailed()
        verify(view).hideProgress()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_gets_the_image_url_which_has_dimen_as_640() {
        val url1 = "http://www.someimage/640/480"
        val url2 = "http://www.someimage/940/480"
        val url3 = "http://www.someimage/1600/900"

        val image1 = Images(url1)
        val image2 = Images(url2)
        val image3 = Images(url3)

        val images = ArrayList<Images>()
        images.add(image1)
        images.add(image2)
        images.add(image3)

        val vehicle = createVehicle(images)
        val imageUrl = presenter.getVehicleImageUrl(vehicle)
        Assert.assertEquals(imageUrl, url1)
    }

    private fun createVehicleViewModel(vehicle: Vehicle): VehicleViewModel {
        return VehicleViewModel(vehicle)
    }

    private fun createVehicle(images: ArrayList<Images> = ArrayList()): Vehicle {
        val seller = Seller("Single", "1234566778", "Berlin")
        return Vehicle(1, "BMW", "6 series",
                "SomeModel", 300000, "",
                2000, "Gasoline", seller, images, "some description")
    }
}