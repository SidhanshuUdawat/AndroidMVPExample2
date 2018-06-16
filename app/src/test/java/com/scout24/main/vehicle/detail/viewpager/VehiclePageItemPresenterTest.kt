package com.scout24.main.vehicle.detail.viewpager

import com.scout24.R
import com.scout24.datasets.Images
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Sid on 16/06/2018.
 */
class VehiclePageItemPresenterTest {

    private lateinit var view: VehiclePageItemMvp.View
    private lateinit var presenter: VehiclePageItemPresenter

    @Before
    fun setUp() {
        view = mock(VehiclePageItemMvp.View::class.java)
        presenter = VehiclePageItemPresenter(view)
    }

    @Test
    fun it_loads_image_from_url_when_bind() {
        val images = Images("http://some_image_url.com")
        presenter.bind(images)
        verify(view).loadImage(images.url)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_loads_image_from_resource_when_image_url_is_empty() {
        val images = Images("")
        presenter.bind(images)
        verify(view).hideProgress()
        verify(view).loadImage(R.drawable.no_image_available)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun it_hides_progress_when_image_loaded() {
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
}