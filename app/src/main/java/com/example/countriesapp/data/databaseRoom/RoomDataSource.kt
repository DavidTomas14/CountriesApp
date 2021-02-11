package com.example.countriesapp.data.databaseRoom

import com.example.countriesapp.data.server.toDomainCountry
import com.example.countriesapp.data.server.toRoomCountry
import com.example.data1.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.Country as Country

class RoomDataSource(db : CountriesDatabase): LocalDataSource {

    private val countryDao = db.countryDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) {countryDao.countryCount()<=0}

    override suspend fun saveCountries(countries: List<Country>) {
        withContext(Dispatchers.IO){countryDao.insertCountries(countries.map { it.toRoomCountry()})}
    }

    override suspend fun getCountries(): List<Country> =
        withContext(Dispatchers.IO){countryDao.getAll().map { it.toDomainCountry() }
        }

    override suspend fun findById(id: Int): Country =
        withContext(Dispatchers.IO){countryDao.findbyId(id).toDomainCountry()}

    override suspend fun update(country: Country) {
        withContext(Dispatchers.IO){countryDao.updateCountry(country.toRoomCountry())
        }
    }

}

