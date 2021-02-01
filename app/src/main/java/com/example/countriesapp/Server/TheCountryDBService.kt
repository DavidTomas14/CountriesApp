package com.example.countriesapp.Server

import retrofit2.http.Query
import retrofit2.http.GET

interface TheCountryDBService {
    @GET("eu")
    suspend fun listCountries(): CountriesApiResult
}