package com.example.countriesapp.model.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name:String,
    val code:String,
    val nativeName:String,
    val capital:String,
    val population:Int,
    val language: String,
    val region: String,
    val subRegion: String,
    val area: Int,
    val flagPath:String
): Parcelable


