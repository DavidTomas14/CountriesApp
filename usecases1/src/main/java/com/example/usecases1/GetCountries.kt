package com.example.usecases1

import com.example.data1.CountriesRepository
import com.example.domain.Country

class GetCountries(
    private val countriesRepository: CountriesRepository) {

    suspend  fun invoke(): List<Country> =
        countriesRepository.getCountries()


}