package com.example.countriesapp.data.databaseRoom

import androidx.lifecycle.LiveData
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

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertCountry(countries: Country)

    @Query("DELETE FROM Country WHERE id = :id" )
    fun deleteById(id: Int)

    @Update
    fun updateCountry(country: Country)

    @Query("SELECT * FROM Country")
    fun observeCountries(): LiveData<List<Country>>

}