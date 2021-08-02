package com.ceiba.domain.model.parking

import com.ceiba.domain.model.Vehicle
import java.util.*

class ParkingVehicle(
    private val vehicle: Vehicle,
    private var dateEntry: Calendar
)