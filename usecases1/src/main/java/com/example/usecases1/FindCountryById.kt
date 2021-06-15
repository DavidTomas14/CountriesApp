package com.example.usecases1

import com.example.data1.CountriesRepository
import com.example.data1.RepositoryInterface
import com.example.domain.Country

class FindCountryById(private val countriesRepository: RepositoryInterface) {
    suspend fun invoke(id:Int): Country = countriesRepository.findById(id)
}