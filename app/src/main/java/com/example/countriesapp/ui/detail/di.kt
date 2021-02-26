package com.example.countriesapp.ui.detail

import com.example.data1.CountriesRepository
import com.example.usecases1.FindCountryById
import com.example.usecases1.ToggleCountryFavorite
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val countryId:Int){

    @Provides
    fun detailViewModelProvider(
            findCountryById : FindCountryById,
            toggleCountryFavorite : ToggleCountryFavorite
    ): DetailViewModel{
        return DetailViewModel(countryId, findCountryById, toggleCountryFavorite)
    }

    @Provides
    fun findCountryIdProvider(countriesRepository : CountriesRepository) = FindCountryById(countriesRepository)

    @Provides
    fun toggleCountriesFavoriteProvider(countriesRepository : CountriesRepository) =ToggleCountryFavorite(countriesRepository)
}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent{
    val detailViewModel: DetailViewModel
}

