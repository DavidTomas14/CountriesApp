package com.example.countriesapp.ui.detail

import android.app.Activity
import com.example.data1.CountriesRepository
import com.example.usecases1.FindCountryById
import com.example.usecases1.ToggleCountryFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class DetailActivityModule {

    @Provides
    @Named("countryId")
    fun getCountryId(activity: Activity)= activity.intent.getIntExtra(DetailActivity.EXTRA_COUNTRY_ID, -1)
}

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityRetainedModule{

    @Provides
    fun findCountryIdProvider(countriesRepository : CountriesRepository) = FindCountryById(countriesRepository)

    @Provides
    fun toggleCountriesFavoriteProvider(countriesRepository : CountriesRepository) =ToggleCountryFavorite(countriesRepository)
}


