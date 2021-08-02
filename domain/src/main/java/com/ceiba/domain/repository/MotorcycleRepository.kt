package com.ceiba.domain.repository

import com.ceiba.domain.model.Motorcycle

interface MotorcycleRepository {
    fun getAll(): List<Motorcycle>
    fun getCount(): Int
    fun save(motorcycle: Motorcycle)
    fun delete(id: Int)
}