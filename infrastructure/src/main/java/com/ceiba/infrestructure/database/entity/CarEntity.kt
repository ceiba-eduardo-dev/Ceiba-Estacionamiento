package com.ceiba.infrestructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_entity")
class CarEntity(
    @ColumnInfo(name = "license_plate")
    val licensePlate: String,
    @ColumnInfo(name = "date_entry")
    val dateEntry: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}