package com.example.countriesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.Server.CountriesRepository
import com.example.countriesapp.model.Database.Country
import com.example.countriesapp.ui.common.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val countriesRepository: CountriesRepository) : ViewModel(),
        Scope by Scope.Impl(){

    sealed class UiModel{
        object Loading:UiModel()
        class Content(val countries: List<Country>):UiModel()
        class Navigation(val country: Country):UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if(_model.value == null) refresh()
            return _model
        }

    init {
        initScope()
    }



    private fun refresh(){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(countriesRepository.findCountries())
        }

    }

    fun onMovieClicked(country: Country){
        _model.value = UiModel.Navigation(country)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }



}