package com.example.countriesapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.ui.common.Event
import com.example.countriesapp.ui.common.Scope
import com.example.countriesapp.ui.common.ScopedViewModel
import com.example.domain.Country
import com.example.usecases1.GetCountries
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val getCountries: GetCountries,
    override val uiDispatcher: CoroutineDispatcher)
    :ScopedViewModel(uiDispatcher){

    sealed class UiModel{
        object Loading:UiModel()
        class Content(val countries: List<Country>):UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if(_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<Country>>()
    val navigation: LiveData<Event<Country>> = _navigation

    init {
        initScope()
    }



    fun refresh(){
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getCountries.invoke())
        }

    }

    fun onMovieClicked(country: Country){
        _navigation.value = Event(country)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }



}