package com.example.countriesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.CountryApp
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.model.server.CountriesRepository
import com.example.countriesapp.ui.detail.DetailActivity
import com.example.countriesapp.ui.main.MainViewModel.UiModel.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(CountriesRepository(application as CountryApp))
        )[MainViewModel::class.java]

        adapter = CountriesAdapter(emptyList(),viewModel::onMovieClicked)
        binding.recyclerView.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, Observer {event->
            event.getContentIfNotHandled()?.let{
                Intent(this, DetailActivity::class.java).run {
                    putExtra(DetailActivity.EXTRA_COUNTRY, it)
                    startActivity(this)
                }
            }
        })


    }

    fun updateUi(model:MainViewModel.UiModel) {
        binding.progress.visibility = if(model == Loading) View.VISIBLE else View.GONE
        when(model){
            is Content -> {
                adapter.items = model.countries
                adapter.notifyDataSetChanged()
            }

        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val countriesRepository: CountriesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(countriesRepository) as T
}
