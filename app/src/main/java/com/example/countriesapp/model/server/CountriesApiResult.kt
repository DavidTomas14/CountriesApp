package com.example.countriesapp.model.server

import com.google.gson.annotations.SerializedName

class CountriesApiResult : ArrayList<CountriesApiResultItem>()

data class CountriesApiResultItem(
        @SerializedName("alpha2Code")
    val Code: String,
        val alpha3Code: String,
        val altSpellings: List<String>,
        @SerializedName("area")
    val Area: Int,
        val borders: List<String>,
        val callingCodes: List<String>,
        @SerializedName("capital")
    val Capital: String,
        val cioc: String,
        val currencies: List<Currency>,
        val demonym: String,
        @SerializedName("flag")
    val Flag: String,
        val gini: Float,
        val languages: List<Language>,
        val latlng: List<Float>,
        @SerializedName("name")
    val Name: String,
        @SerializedName("nativeName")
    val NativeName: String,
        val numericCode: String,
        @SerializedName("population")
    val Population: Int,
        @SerializedName("region")
    val Region: String,
        val regionalBlocs: List<RegionalBloc>,
        @SerializedName("subregion")
    val Subregion: String,
        val timezones: List<String>,
        val topLevelDomain: List<String>,
        val translations: Translations
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)

data class RegionalBloc(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<Any>,
    val otherNames: List<Any>
)

data class Translations(
    val br: String,
    val de: String,
    val es: String,
    val fa: String,
    val fr: String,
    val hr: String,
    val `it`: String,
    val ja: String,
    val nl: String,
    val pt: String
)