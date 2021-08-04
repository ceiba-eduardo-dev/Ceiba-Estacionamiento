package com.ceiba.infrestructure.repository

import android.content.Context
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.repository.MotorcycleRepository
import com.ceiba.infrestructure.anticorruption.MotorcycleTranslator
import com.ceiba.infrestructure.database.ParkingDatabase
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext

class MotorcycleRepositoryRoom @Inject constructor(@ApplicationContext private val context: Context) :
    MotorcycleRepository {

    val database = ParkingDatabase.get(context)
    override fun getAll(): List<Motorcycle> {
        val listMotorcyclesDomain = mutableListOf<Motorcycle>()
        listMotorcyclesDomain.addAll(
            MotorcycleTranslator.fromListEntityToListDomain(
                database.motorcycleDao().getAllMotorcycles()
            )

        )
        return listMotorcyclesDomain
    }

    override fun getCount(): Int = database.motorcycleDao().getCountTotalMotorcycles()

    override fun save(motorcycle: Motorcycle) {
        database.motorcycleDao().saveMotorcycle(MotorcycleTranslator.fromDomainToEntity(motorcycle))
    }

    override fun delete(motorcycle: Motorcycle) {
        database.motorcycleDao()
            .deleteMotorcycle(MotorcycleTranslator.fromDomainToEntity(motorcycle).id)
    }

    override fun getByLicensePlate(licensePlate: String): Motorcycle {
        return MotorcycleTranslator.fromEntityToDomain(
            database.motorcycleDao().getByLicensePlate(licensePlate)
        )
    }

}