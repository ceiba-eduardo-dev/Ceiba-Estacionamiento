package com.ceiba.infrestructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_entity")
class CarEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "licensePlate")
    val licensePlate: String? = null

    @ColumnInfo(name = "dateEntry")
    var dateEntry: String? = null
}