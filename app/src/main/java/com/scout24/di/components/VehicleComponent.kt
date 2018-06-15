package com.scout24.di.components

import com.scout24.di.modules.VehicleModule
import com.scout24.di.provider.ApplicationBaseComponent
import com.scout24.di.scopes.PerActivity
import com.scout24.main.vehicle.VehicleActivity
import dagger.Component

/**
 * Created by Sid on 14/06/2018.
 */

@PerActivity
@Component(dependencies = [(ApplicationBaseComponent::class)], modules = [(VehicleModule::class)])
interface VehicleComponent {
    fun inject(vehicleActivity: VehicleActivity)
}