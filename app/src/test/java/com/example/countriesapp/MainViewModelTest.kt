package com.example.countriesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.countriesapp.repositories.FakeRepository
import com.example.countriesapp.repositories.countriesTest
import com.example.countriesapp.ui.common.Event
import com.example.countriesapp.ui.main.MainViewModel
import com.example.countriesapp.ui.main.MainViewModel.*
import com.example.domain.Country
import com.example.usecases1.GetCountries
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var vm: MainViewModel

    @Before
    fun setup(){
        vm = MainViewModel(GetCountries(FakeRepository()), Dispatchers.Unconfined)
    }


    @Test
    fun `observing  getCountries`(){
        runBlocking {
            val countries = mutableListOf<Country>(
                Country( 0, "España","ES", "España", "Madrid", 47000000, "castellano", "Europa", "Suroeste Europa", 1239834, "flagpath", true),
                Country( 1, "España","ES", "España", "Madrid", 47000000, "castellano", "Europa", "Suroeste Europa", 1239834, "flagpath", true),
                Country( 2, "España","ES", "España", "Madrid", 47000000, "castellano", "Europa", "Suroeste Europa", 1239834, "flagpath", true),
                Country( 3, "España","ES", "España", "Madrid", 47000000, "castellano", "Europa", "Suroeste Europa", 1239834, "flagpath", true)
            )
            vm.refresh()
            val value = vm.model.getOrAwaitValueTest()

            var contentCountries : List<Country>? = null
            if(value is UiModel.Content){
                contentCountries = value.countries
            }

            assertThat(contentCountries).isEqualTo(countriesTest)
        }
    }

    @Test
    fun `observing onMovieClicked`() {
        runBlocking {
            val country = Country( 0, "España","ES", "España", "Madrid", 47000000, "castellano", "Europa", "Suroeste Europa", 1239834, "flagpath", true)
            vm.onMovieClicked(country)

            val value = vm.navigation.getOrAwaitValueTest()

            assertThat(value.getContentIfNotHandled()).isEqualTo(country)
        }
    }

}

val mockedCountry = Country(
    0,
    "España",
    "ES",
    "España",
    "Madrid",
    47000000,
    "castellano",
    "Europa",
    "Suroeste Europa",
    1239834,
    "flagpath",
    true
)