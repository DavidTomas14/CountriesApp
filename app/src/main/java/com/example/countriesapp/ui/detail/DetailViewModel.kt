package com.example.countriesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.model.Database.Country

class DetailViewModel(private val country: Country) : ViewModel() {

    class UiModel(val country: Country)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(country)
            return _model
        }
}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val country: Country) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(country) as T
}
