package com.ozerbayraktar.nbaplayersapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerbayraktar.nbaplayersapp.domain.model.Data
import com.ozerbayraktar.nbaplayersapp.data.repository.NbaPlayersRepository
import com.ozerbayraktar.nbaplayersapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NbaPlayersViewModel @Inject constructor(
    private val repository: NbaPlayersRepository
):ViewModel(){

    var playersList= mutableStateOf<List<Data>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadPlayers()
    }

    fun loadPlayers() {
        viewModelScope.launch {
            isLoading.value=true
            val result = repository.getNbaPlayersList()


            when(result){
                is Resource.Success -> {
                    val playerItems=result.data!!.data.mapIndexed { index, data ->
                        Data(data.first_name,data.height_feet,data.height_inches,data.id,data.last_name,data.position,data.team,data.weight_pounds)
                    }as List<Data>

                    playersList.value +=playerItems
                    errorMessage.value = ""
                    isLoading.value=false


                }
                is Resource.Error -> {
                    isLoading.value = false
                    errorMessage.value=result.message!!


            }
            }
        }

    }
}