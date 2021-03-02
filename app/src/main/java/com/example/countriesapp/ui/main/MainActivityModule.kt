package com.example.countriesapp.ui.main

import com.example.data1.CountriesRepository
import com.example.usecases1.GetCountries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule{

    @Provides
    fun getCountriesProvider(countriesRepository : CountriesRepository) = GetCountries(countriesRepository)

}