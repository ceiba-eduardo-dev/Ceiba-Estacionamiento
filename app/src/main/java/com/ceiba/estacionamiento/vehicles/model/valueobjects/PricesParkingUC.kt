package com.ceiba.estacionamiento.vehicles.model.valueobjects

import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.bike
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.minHoursDay
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.priceBikeDay
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.priceBikeHour
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.priceCarDay
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.priceCarHour
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.totalHoursDay
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity

class PricesParkingUC {
    fun calculatePrice(hours: Int, vehicle: VehicleEntity): Int {
        var price = 0
        if (hours != null && vehicle != null && vehicle.type != null) {
            var priceDay = 0
            var priceHour = 0
            if (vehicle.type == car) {
                priceDay = priceCarDay
                priceHour = priceCarHour
            }
            if (vehicle.type == bike) {
                priceDay = priceBikeDay
                priceHour = priceBikeHour
            }
            if (hours >= minHoursDay && hours > totalHoursDay) {
                var residue = hours % totalHoursDay
                var totalDays = hours / totalHoursDay
                price = if (residue == 0) {
                    totalDays * priceDay
                } else {
                    (totalDays * priceDay) + (residue * priceHour)
                }
            }
            if (hours in minHoursDay..totalHoursDay) {
                price = priceDay;
            }
            if (hours < minHoursDay) {
                price = hours * priceHour
            }
        }
        return price
    }

    /**
     * Las motos que tengan un cilindraje mayor a 500 CC paga 2000 de mas al valor total.
     */
    fun aggregatePriceForCc(price: Int, vehicle: VehicleEntity?): Int {
        if (vehicle?.type == bike && vehicle?.cylinder >= 500) {
            return price + 2000;
        }
        return price
    }


}