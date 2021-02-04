package com.example.countriesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.model.databaseRoom.Country
import com.example.countriesapp.model.server.CountriesRepository
import com.example.countriesapp.ui.common.Scope
import kotlinx.coroutines.launch

class DetailViewModel(private val countryId: Int, private val countriesRepository: CountriesRepository)
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
        _model.value = UiModel(countriesRepository.findById(countryId))
    }

}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val countryId: Int,
                             private val countriesRepository: CountriesRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(countryId, countriesRepository) as T
}
