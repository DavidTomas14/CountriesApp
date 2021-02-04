package com.example.countriesapp.model.server

import com.example.countriesapp.model.databaseRoom.Country
import com.example.countriesapp.CountryApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository(application: CountryApp) {

    private val db = application.db

    suspend fun findCountries(): List<Country> = withContext(Dispatchers.IO){

        with(db.countryDao()) {
            if(countryCount() <= 0){
                val countries = CountryApiRetrofit.service
                        .listCountries()
                        .map(CountriesApiResultItem::convertToCountry)

                insertCountries(countries)
            }
            getAll()
        }
    }
    suspend fun findById(id: Int) : Country = withContext(Dispatchers.IO){
        db.countryDao().findbyId(id)
    }

    suspend fun update(country: Country) = withContext(Dispatchers.IO){
        db.countryDao().updateCountry(country)
    }

    }
private fun CountriesApiResultItem.convertToCountry() = Country(
        0,
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