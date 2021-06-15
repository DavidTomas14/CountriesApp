package com.example.countriesapp.di

import android.app.Application
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
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun databaseProvider(app: Application) : CountriesDatabase = Room.databaseBuilder(
        app,
        CountriesDatabase::class.java,
        "countries-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: CountriesDatabase) : LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = TheCountryServerDataSource()

}