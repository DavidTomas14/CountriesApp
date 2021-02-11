package com.example.data1

import com.example.domain.Country


interface RemoteDataSource {
    suspend fun getCountries(): List<Country>
}