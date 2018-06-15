package com.scout24.main

import com.scout24.main.vehicle.VehicleLocalDataSource
import com.scout24.main.vehicle.VehicleMvp
import com.scout24.realm.RealmMvp
import com.scout24.sharedpref.SharedPreferencesProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Sid on 15/06/2018.
 */
class VehicleLocalDataSourceTest {

    private lateinit var realmManager: RealmMvp
    private lateinit var pref: SharedPreferencesProvider
    private lateinit var localDataSource: VehicleMvp.LocalDataSource

    @Before
    fun setUp() {
        realmManager = mock(RealmMvp::class.java)
        pref = mock(SharedPreferencesProvider::class.java)
        localDataSource = VehicleLocalDataSource(realmManager, pref)
    }

    @Test
    fun it_gets_boolean_data_from_preference_when_checking_for_local_data() {
        localDataSource.hasLocalData()
        verify(pref).getBooleanData(VehicleLocalDataSource.IS_LOCAL_DATA_SAVED)
        verifyNoMoreInteractions(pref)
    }
}