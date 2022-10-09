package com.ozerbayraktar.nbaplayersapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ozerbayraktar.nbaplayersapp.presentation.ui.theme.NbaPlayersAppTheme
import com.ozerbayraktar.nbaplayersapp.presentation.view.NbaPlayersListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaPlayersAppTheme {
                NbaPlayersListScreen()
                println("ekran açıldı")

            }
        }
    }
}
