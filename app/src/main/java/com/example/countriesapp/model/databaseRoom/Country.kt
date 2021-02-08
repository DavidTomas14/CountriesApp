package com.example.countriesapp.model.databaseRoom

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Country(
        @PrimaryKey(autoGenerate = true) val id:Int,
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
        val favourite: Boolean = false
): Parcelable