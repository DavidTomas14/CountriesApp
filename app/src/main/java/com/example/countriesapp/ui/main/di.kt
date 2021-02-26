package com.example.countriesapp.ui.main

import com.example.data1.CountriesRepository
import com.example.usecases1.GetCountries
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class MainActivityModule{

    @Provides
    fun mainViewModelProvider(getCountries : GetCountries) = MainViewModel(getCountries)

    @Provides
    fun getCountriesProvider(countriesRepository : CountriesRepository) = GetCountries(countriesRepository)

}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent{
    val mainViewModel : MainViewModel
}