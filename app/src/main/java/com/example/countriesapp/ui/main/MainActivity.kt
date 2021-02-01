package com.example.countriesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.Server.CountriesRepository
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.model.Database.Country
import com.example.countriesapp.ui.detail.DetailActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countriesRepository = CountriesRepository()
        val countriesAdapter = CountriesAdapter(emptyList()){country->
            navigateTo(country)
        }

        binding.recyclerView.adapter = countriesAdapter


        lifecycleScope.launch() {
            var countries = countriesRepository.findCountries()
            countriesAdapter.items = countries
            countriesAdapter.notifyDataSetChanged()
        }




    }

    private fun navigateTo(country: Country) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_COUNTRY, country)
        startActivity(intent)

    }
}