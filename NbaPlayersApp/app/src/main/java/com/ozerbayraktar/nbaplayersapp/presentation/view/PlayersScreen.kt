package com.ozerbayraktar.nbaplayersapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozerbayraktar.nbaplayersapp.domain.model.Data
import com.ozerbayraktar.nbaplayersapp.presentation.viewmodels.NbaPlayersViewModel



@Composable
fun NbaPlayersListScreen(
    viewModel: NbaPlayersViewModel= hiltViewModel()
) {
    Surface(color = MaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text("Nba Players", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                 textAlign = TextAlign.Center,
                 fontSize = 45.sp,
                 fontWeight = FontWeight.Bold,
                 color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            PlayersList()
        }

    }

}

@Composable
fun PlayersRow(
    player:Data
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        shape= RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {


            Column(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green)
            ) {
                Text(
                    text ="İsim: ${player.first_name} ${player.last_name}",
                    style=MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(2.dp),
                    color= Color.Red
                )
                Text(
                    text = "Takımı: ${player.team.name}",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black
                )
            }
        }

    }


}


//viewmodelin kullanıldığı yer.
@Composable
fun PlayersList(
    viewModel: NbaPlayersViewModel= hiltViewModel()
){
    val playersList by remember { viewModel.playersList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    PlayersListview(players = playersList)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        if (isLoading){
            CircularProgressIndicator()
        }
        if(errorMessage.isNotEmpty()) {
            RetryView(error = errorMessage) {
                viewModel.loadPlayers()
            }
        }

    }
}


@Composable
fun PlayersListview(players:List<Data>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        items(players){ it ->
            PlayersRow(player =it)

        }
    }

}
@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}