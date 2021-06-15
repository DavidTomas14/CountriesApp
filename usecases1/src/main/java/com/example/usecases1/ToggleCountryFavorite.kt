package com.example.usecases1

import com.example.data1.CountriesRepository
import com.example.data1.RepositoryInterface
import com.example.domain.Country

class ToggleCountryFavorite(private val countriesRepository: RepositoryInterface) {
    suspend fun invoke(country:Country): Country = with(country){
        copy(favourite = !favourite).also { countriesRepository.update(it) }
    }
}