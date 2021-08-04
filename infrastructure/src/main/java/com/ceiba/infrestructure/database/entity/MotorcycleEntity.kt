package com.ceiba.infrestructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle_entity")
class MotorcycleEntity(
    @ColumnInfo(name = "licensePlate")
    val licensePlate: String,
    @ColumnInfo(name = "cylinderCapacity")
    val cylinderCapacity: Int,
    @ColumnInfo(name = "dateEntry")
    val dateEntry: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0
}
