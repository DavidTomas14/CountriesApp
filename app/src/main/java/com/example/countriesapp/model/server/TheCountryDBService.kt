package com.example.countriesapp.model.server

import retrofit2.http.GET

interface TheCountryDBService {
    @GET("eu")
    suspend fun listCountries(): CountriesApiResult
}