package com.example.countriesapp.di

import com.example.data1.CountriesRepository
import com.example.data1.LocalDataSource
import com.example.data1.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun countriesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = CountriesRepository(localDataSource, remoteDataSource)

}