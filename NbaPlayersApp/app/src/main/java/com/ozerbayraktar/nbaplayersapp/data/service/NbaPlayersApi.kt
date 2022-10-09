package com.ozerbayraktar.nbaplayersapp.data.service

import com.ozerbayraktar.nbaplayersapp.domain.model.NbaPlayersList
import retrofit2.http.GET

interface NbaPlayersApi {

    @GET("players?rapidapi-key=57279d9ad9mshd15047d91ca44aap18a567jsn3fb9e681e9be")
    suspend fun getPlayersList() : NbaPlayersList


}