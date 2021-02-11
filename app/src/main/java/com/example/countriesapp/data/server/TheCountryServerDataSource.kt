package com.example.countriesapp.data.server

import com.example.data1.RemoteDataSource
import com.example.domain.Country

class TheCountryServerDataSource : RemoteDataSource{

    override suspend fun getCountries(): List<Country> =
        CountryApiRetrofit.service
            .listCountries()
            .map { it.toDomaiCountry() }
}