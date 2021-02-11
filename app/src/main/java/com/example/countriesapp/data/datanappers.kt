package com.example.countriesapp.data.server

import com.example.domain.Country
import com.example.countriesapp.data.server.CountriesApiResultItem as ServerCountry
import com.example.countriesapp.data.databaseRoom.Country as RoomCountry

fun Country.toRoomCountry() : RoomCountry =
    RoomCountry(
        id,
        name,
        code,
        nativeName,
        capital,
        population,
        language,
        region,
        subRegion,
        area,
        flagPath,
        favourite)
fun RoomCountry.toDomainCountry(): Country = Country(
        id,
        name,
        code,
        nativeName,
        capital,
        population,
        language,
        region,
        subRegion,
        area,
        flagPath,
        favourite

)

fun ServerCountry.toDomaiCountry(): Country = Country(
        0,
        Name,
        Code,
        NativeName,
        Capital,
        Population,
        languages[0].name,
        Region,
        Subregion,
        Area,
        Flag,
        false
        )