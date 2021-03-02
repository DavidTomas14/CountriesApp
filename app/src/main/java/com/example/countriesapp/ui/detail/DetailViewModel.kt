package com.example.countriesapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Country
import com.example.countriesapp.ui.common.Scope
import com.example.usecases1.FindCountryById
import com.example.usecases1.ToggleCountryFavorite
import kotlinx.coroutines.launch
import javax.inject.Named

class DetailViewModel @ViewModelInject constructor(
    @Named("countryId") private val countryId: Int,
    private val findCountryById: FindCountryById,
    private val toggleCountryFavorite: ToggleCountryFavorite)
    : ViewModel(),  Scope by Scope.Impl() {

    class UiModel(val country: Country)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findCountry()
            return _model
        }
    init {
        initScope()
    }

    private fun findCountry() = launch {
        _model.value = UiModel(findCountryById.invoke(countryId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.country?.let {
            _model.value  = UiModel(toggleCountryFavorite.invoke(it))
        }
    }

}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
        private val countryId: Int,
        private val findCountryById: FindCountryById,
        private val toggleCountryFavorite: ToggleCountryFavorite) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(countryId, findCountryById, toggleCountryFavorite) as T
}
