package com.scout24.main

import com.scout24.api.VehicleRequestsInterface
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Observable

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleRemoteDataSourceTest {

    private lateinit var api: VehicleRequestsInterface
    private lateinit var remoteDataSource: VehicleMvp.RemoteDataSource

    @Before
    fun setUp() {
        api = mock(VehicleRequestsInterface::class.java)
        remoteDataSource = VehicleRemoteDataSource(api)
    }

    @Test
    fun it_gets_vehicles() {
        `when`(api.getVehicleList()).thenReturn(Observable.empty())

        remoteDataSource.getVehicles()
        verify(api).getVehicleList()
        verifyNoMoreInteractions(api)
    }

}