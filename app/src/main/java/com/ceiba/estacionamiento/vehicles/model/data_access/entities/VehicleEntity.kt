package com.ceiba.estacionamiento.vehicles.model.data_access.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "vehicles")
open class VehicleEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idVehicle")
    var idVehicle: Int = 0
    var plate: String = ""
    var cylinder: Int = 0
    var type: String = ""
    var date: String = ""
    var hour: String = ""
}