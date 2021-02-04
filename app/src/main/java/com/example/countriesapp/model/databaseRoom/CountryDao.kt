package com.example.countriesapp.model.databaseRoom

import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM Country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM Country WHERE id = :id")
    fun findbyId(id: Int): Country

    @Query("SELECT  COUNT(id) FROM Country")
    fun countryCount(): Int

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertCountries(countries: List<Country>)

    @Update
    fun updateCountry(country: Country)
}