package com.example.countriesapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.countriesapp.data.databaseRoom.CountriesDatabase
import com.example.countriesapp.data.databaseRoom.Country
import com.example.countriesapp.data.databaseRoom.CountryDao
import com.example.countriesapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class CountriesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CountriesDatabase

    private lateinit var dao: CountryDao

    @Before
    fun setUp(){
        hiltRule.inject()
        dao = database.countryDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertCountry() = runBlockingTest {
        val country1 = Country(2, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true)
        val country2 = Country(3, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true)
        dao.insertCountry(country1)
        dao.insertCountry(country2)
        val allCountries = dao.observeCountries().getOrAwaitValue()
        assertThat(allCountries).contains(country1)

    }
    @Test
    fun insertCountries() = runBlockingTest {
        val countries = listOf(
            Country(2, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true),
            Country(3, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true),
            Country(4, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true)
        )
        dao.insertCountries(countries)
        val countryCheck = Country(2, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true)
        val allCountries = dao.observeCountries().getOrAwaitValue()
        assertThat(allCountries).contains(countryCheck)

    }

    @Test
    fun deleteCountry() = runBlockingTest {
        val country = Country(2, "España", "ES", "España", "Madrid", 46000000, "castellano", "noop", "noop", 123456, "url", true)
        dao.insertCountry(country)
        dao.deleteById(country.id)
        val allCountries = dao.observeCountries().getOrAwaitValue()
        assertThat(allCountries).doesNotContain(country)
    }
}