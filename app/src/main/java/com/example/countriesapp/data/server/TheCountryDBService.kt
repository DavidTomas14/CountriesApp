package com.example.countriesapp.data.server

import retrofit2.http.GET

interface TheCountryDBService {
    @GET("eu")
    suspend fun listCountries(): CountriesApiResult
}