package com.ceiba.estacionamiento.vehicles.model.valueobjects

import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.bike
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.monday
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity

class RulesParkingUC {

    /**
     * Las placas que inician por la letra "A" solo pueden ingresar al parqueadero los días Domingo y Lunes,
     * de lo contrario debe mostrar un mensaje de que no esta autorizado a ingresar.
     */
    fun validateAccessForDay(day: String?, type: String, vehicleEntity: VehicleEntity): Boolean {
        val letterInitial = vehicleEntity.plate[0].uppercase()
        return (type == car && day == monday && letterInitial == "A")
    }

    /**
     * El parqueadero solo puede tener 20 carros y 10 motos simultaneamente
     */
    fun validateAccessQuantityByType(vehicles: List<VehicleEntity>?, type: String): Boolean {
        if (vehicles != null) {
            val temp: MutableList<VehicleEntity> = mutableListOf()
            for (vehicle: VehicleEntity in vehicles) {
                if (vehicle.type == type) {
                    temp.add(vehicle)
                }
            }
            return !((type == car && temp.size >= 20) || (type == bike && temp.size >= 10))
        }
        return false
    }


}