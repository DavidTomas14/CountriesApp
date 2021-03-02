package com.example.countriesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.ui.detail.DetailActivity
import com.example.countriesapp.ui.main.MainViewModel.UiModel.*
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.data.databaseRoom.RoomDataSource
import com.example.countriesapp.data.server.TheCountryServerDataSource
import com.example.countriesapp.ui.common.app
import com.example.countriesapp.ui.common.getViewModel
import com.example.countriesapp.ui.common.startActivity
import com.example.data1.CountriesRepository
import com.example.usecases1.GetCountries
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CountriesAdapter(viewModel::onMovieClicked)
        binding.recyclerView.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, Observer {event->
            event.getContentIfNotHandled()?.let{
               startActivity<DetailActivity>{
                    putExtra(DetailActivity.EXTRA_COUNTRY_ID, it.id)
                }
            }
        })


    }

    private fun updateUi(model:MainViewModel.UiModel) {
        binding.progress.visibility = if(model == Loading) View.VISIBLE else View.GONE
        when(model){
            is Content -> {
                adapter.items = model.countries
            }

        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
        private val getCountries: GetCountries): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getCountries) as T
}
