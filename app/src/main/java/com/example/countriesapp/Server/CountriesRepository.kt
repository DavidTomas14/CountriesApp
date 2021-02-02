package com.example.countriesapp.Server

import com.example.countriesapp.model.Database.Country
import kotlinx.coroutines.withContext

class CountriesRepository {
    suspend fun findCountries(): List<Country> {
        return CountryApiRetrofit.service
            .listCountries()
            .map(CountriesApiResultItem::convertToCountry)
    }
}
private fun CountriesApiResultItem.convertToCountry() = Country(
    Name,
    Code,
    NativeName,
    Capital,
    Population,
    languages[0].name,
    Region,
    Subregion,
    Area,
    Flag
)