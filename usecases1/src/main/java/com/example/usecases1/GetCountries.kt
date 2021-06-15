package com.example.usecases1

import com.example.data1.CountriesRepository
import com.example.data1.RepositoryInterface
import com.example.domain.Country

class GetCountries(
    private val countriesRepository: RepositoryInterface) {

    suspend  fun invoke(): List<Country> =
        countriesRepository.getCountries()


}