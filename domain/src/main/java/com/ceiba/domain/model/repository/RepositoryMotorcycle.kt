package com.ceiba.domain.model.repository

import com.ceiba.domain.model.entity.Motorcycle

interface RepositoryMotorcycle {
    fun getAllMotorcycles(): List<Motorcycle>
    fun getCountTotalMotorcycles(): Int
    fun saveMotorcycle(motorcycle: Motorcycle)
    fun deleteMotorcycle(id: Int)
}