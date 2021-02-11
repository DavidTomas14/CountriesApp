package com.example.data1

import com.example.domain.Country

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveCountries(countries: List<Country>)
    suspend fun getCountries(): List<Country>
    suspend fun findById(id:Int): Country
    suspend fun update(country: Country)
}