package com.ceiba.estacionamiento.vehicles.model.dataaccess.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity

@Dao
interface VehicleDao {
    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): List<VehicleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicle(v: VehicleEntity): Long

    @Query("SELECT * FROM vehicles WHERE plate LIKE :plate")
    fun getVehicleByPlate(plate: String): VehicleEntity

    @Query("DELETE FROM vehicles WHERE idVehicle = :id")
    fun deleteVehicle(id: Int)


}
