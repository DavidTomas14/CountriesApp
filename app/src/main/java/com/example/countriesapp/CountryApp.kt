package com.example.countriesapp

import android.app.Application
import androidx.room.Room
import com.example.countriesapp.data.databaseRoom.CountriesDatabase
import com.example.countriesapp.di.DaggerMyCountiresComponent
import com.example.countriesapp.di.MyCountiresComponent

class CountryApp : Application() {

     lateinit var component: MyCountiresComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyCountiresComponent
                .factory()
                .create(this)
    }
}
