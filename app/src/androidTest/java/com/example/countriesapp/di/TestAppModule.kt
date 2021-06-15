package com.example.countriesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.countriesapp.data.databaseRoom.CountriesDatabase
import com.example.countriesapp.data.databaseRoom.RoomDataSource
import com.example.countriesapp.data.server.TheCountryServerDataSource
import com.example.data1.LocalDataSource
import com.example.data1.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ) = Room.inMemoryDatabaseBuilder(context, CountriesDatabase::class.java)
        .allowMainThreadQueries()
        .build()

}