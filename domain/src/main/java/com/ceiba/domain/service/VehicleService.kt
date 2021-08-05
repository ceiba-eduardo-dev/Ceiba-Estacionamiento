package com.ceiba.domain.service

abstract class VehicleService {
    companion object {
        const val minHoursDay = 9;
        const val totalHoursDay = 24
    }

    fun calculatePriceForHours(hours: Int, priceDay: Int, priceHour: Int): Int {
        var price = 0
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
        return price
    }
}