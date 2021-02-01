package com.example.countriesapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.Server.CountriesRepository
import com.example.countriesapp.model.Database.Country

class MainViewModel : ViewModel(){

    private lateinit var countriesRepository: CountriesRepository

    private var _countries :MutableList<Country> = mutableListOf()
    val countries
        get() = _countries


}