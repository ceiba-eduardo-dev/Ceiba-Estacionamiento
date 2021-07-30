package com.ceiba.estacionamiento.vehicles.controller.dimodule

import com.ceiba.estacionamiento.vehicles.model.data_access.repository.VehicleRepositoryRoom
import com.ceiba.estacionamiento.vehicles.model.repositories.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
abstract class VehiclesModule {
    @Binds
    abstract fun provideVehicleRepository(vehicleRepositoryRoom: VehicleRepositoryRoom): VehicleRepository
}