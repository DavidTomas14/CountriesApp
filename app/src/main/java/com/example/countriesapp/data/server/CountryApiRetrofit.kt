package com.example.countriesapp.data.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryApiRetrofit {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val service: TheCountryDBService = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/rest/v2/regionalbloc/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<TheCountryDBService>(TheCountryDBService::class.java)
        }
}