package com.ceiba.domain.service

import com.ceiba.domain.model.Motorcycle

class ParkingMotorcycleService{
    companion object {
        const val maxCylinderCapacity = 500
        const val priceAggregate = 2000
    }

    fun getPriceByCylinderCapacity(motorcycle: Motorcycle): Int {
        if (motorcycle.getCylinderCapacity() > maxCylinderCapacity)
            return priceAggregate
        return 0
    }
}