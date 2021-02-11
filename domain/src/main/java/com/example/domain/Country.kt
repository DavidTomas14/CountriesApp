package com.example.domain


data class Country(
    val id:Int,
    val name:String,
    val code: String,
    val nativeName: String,
    val capital:String,
    val population:Int,
    val language:String,
    val region:String,
    val subRegion: String,
    val area:Int,
    val flagPath: String,
    val favourite: Boolean
)