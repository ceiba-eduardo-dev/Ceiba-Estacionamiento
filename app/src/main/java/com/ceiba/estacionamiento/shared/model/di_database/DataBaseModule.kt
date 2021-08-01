package com.ceiba.estacionamiento.vehicles.controller.di.module

import android.content.Context
import com.ceiba.estacionamiento.vehicles.model.dataaccess.daos.VehicleDao
import com.ceiba.estacionamiento.shared.model.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getInstance(context)

    @Provides
    fun provideVehicleDao(appDataBase: AppDataBase): VehicleDao = appDataBase.vehicleDao()

}