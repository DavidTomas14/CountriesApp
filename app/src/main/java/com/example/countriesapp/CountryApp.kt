package com.example.countriesapp

import android.app.Application
import androidx.room.Room
import com.example.countriesapp.model.databaseRoom.CountriesDatabase

class CountryApp : Application() {

     lateinit var db: CountriesDatabase
        private set

    override fun onCreate() {
     db = Room.databaseBuilder(
            this,
            CountriesDatabase::class.java, "countries-db"
        ).build()

        super.onCreate()
    }
}
