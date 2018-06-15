package com.scout24.main

import com.scout24.main.vehicle.VehicleInteractor
import com.scout24.main.vehicle.VehicleMvp
import com.scout24.network.NetworkMonitorProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Observable

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleInteractorTest {

    private lateinit var remoteDataSource: VehicleMvp.RemoteDataSource
    private lateinit var localDataSource: VehicleMvp.LocalDataSource
    private lateinit var networkMonitorProvider: NetworkMonitorProvider
    private lateinit var interactor: VehicleMvp.Interactor

    @Before
    fun setUp() {
        remoteDataSource = mock(VehicleMvp.RemoteDataSource::class.java)
        localDataSource = mock(VehicleMvp.LocalDataSource::class.java)
        networkMonitorProvider = mock(NetworkMonitorProvider::class.java)
        interactor = VehicleInteractor(remoteDataSource, localDataSource, networkMonitorProvider)
    }


    @Test
    fun it_fetches_remote_vehicles_when_network_is_connected() {
        `when`(networkMonitorProvider.isConnected()).thenReturn(true)
        `when`(remoteDataSource.getVehicles()).thenReturn(Observable.empty())

        interactor.getVehicles()
        verify(networkMonitorProvider).isConnected()
        verify(remoteDataSource).getVehicles()
        verifyNoMoreInteractions(networkMonitorProvider, remoteDataSource)
    }

    @Test
    fun it_fetches_local_vehicles_when_network_is_not_connected_and_device_has_local_data() {
        `when`(networkMonitorProvider.isConnected()).thenReturn(false)
        `when`(localDataSource.hasLocalData()).thenReturn(true)
        `when`(localDataSource.getVehicles()).thenReturn(Observable.empty())

        interactor.getVehicles()
        verify(networkMonitorProvider).isConnected()
        verify(localDataSource).hasLocalData()
        verify(localDataSource).getVehicles()
        verifyNoMoreInteractions(networkMonitorProvider, localDataSource)
    }
}