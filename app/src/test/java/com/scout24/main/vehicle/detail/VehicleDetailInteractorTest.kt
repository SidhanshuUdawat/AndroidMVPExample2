package com.scout24.main.vehicle.detail

import com.scout24.main.vehicle.VehicleMvp
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Observable

/**
 * Created by Sid on 15/06/2018.
 */
class VehicleDetailInteractorTest {

    private lateinit var localDataSource: VehicleMvp.LocalDataSource
    private lateinit var interactor: VehicleDetailInteractor
    private val vehicleId = 200

    @Before
    fun setUp() {
        localDataSource = mock(VehicleMvp.LocalDataSource::class.java)
        interactor = VehicleDetailInteractor(localDataSource, vehicleId)
    }

    @Test
    fun it_gets_vehicle_from_local_data_source() {
        `when`(interactor.getVehicle()).thenReturn(Observable.empty())

        interactor.getVehicle()
        verify(localDataSource).getVehichle(vehicleId)
        verifyNoMoreInteractions(localDataSource)
    }
}