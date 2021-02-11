package com.example.countriesapp.data.databaseRoom

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Country::class], version = 1)
abstract class CountriesDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDao
}