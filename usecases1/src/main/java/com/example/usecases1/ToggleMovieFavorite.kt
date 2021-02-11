package com.example.usecases1

import com.example.data1.CountriesRepository
import com.example.domain.Country

class ToggleMovieFavorite(private val countriesRepository: CountriesRepository) {
    suspend fun invoke(country:Country): Country = with(country){
        copy(favourite = !favourite).also { countriesRepository.update(it) }
    }
}