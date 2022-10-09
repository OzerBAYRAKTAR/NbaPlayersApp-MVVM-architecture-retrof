package com.ozerbayraktar.nbaplayersapp.data.repository

import com.ozerbayraktar.nbaplayersapp.domain.model.NbaPlayersList
import com.ozerbayraktar.nbaplayersapp.data.service.NbaPlayersApi
import com.ozerbayraktar.nbaplayersapp.util.Resource
import javax.inject.Inject


class NbaPlayersRepository @Inject constructor(
    private val api: NbaPlayersApi
){
    suspend fun getNbaPlayersList(): Resource<NbaPlayersList> {
        val response = try {
            api.getPlayersList()
        } catch (e: Exception) {
            return Resource.Error("Something went wrong")
        }
        return Resource.Success(response)
    }
}