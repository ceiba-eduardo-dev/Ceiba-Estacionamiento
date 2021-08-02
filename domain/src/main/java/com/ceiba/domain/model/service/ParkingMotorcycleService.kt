package com.ceiba.domain.model.service

import com.ceiba.domain.model.entity.Motorcycle
import com.ceiba.domain.model.repository.RepositoryMotorcycle

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