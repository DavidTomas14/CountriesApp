package com.example.data1

import com.example.domain.Country

class CountriesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource:RemoteDataSource) {

    suspend fun getCountries(): List<Country>{
        if(localDataSource.isEmpty()){

            val countries = remoteDataSource.getCountries()

            localDataSource.saveCountries(countries)
        }
        return localDataSource.getCountries()
    }

    suspend fun findById(id:Int): Country = localDataSource.findById(id)

    suspend fun update(country: Country) = localDataSource.update(country)
}


