package com.test.simplegame.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.simplegame.AppPrefs
import com.test.simplegame.NavControllerRoutes

@Composable
fun ResultScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    latestPlayerScore: Int
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${AppPrefs.getNameOfLatestPlayer()}'s Score:", fontSize = 25.sp)
        Spacer(Modifier.size(20.dp))
        Text("$latestPlayerScore", fontSize = 25.sp)
        Spacer(Modifier.size(20.dp))
        Text("Highest Score:", fontSize = 25.sp)
        Text("(${AppPrefs.getNameOfBestPlayer()})<<${AppPrefs.getScoreOfBestPlayer()}>>", fontSize = 25.sp)
        Spacer(Modifier.size(20.dp))
        Text("Play Count:", fontSize = 25.sp)
        Text("${AppPrefs.getNumberOfCounter()}", fontSize = 25.sp)
        Spacer(Modifier.size(20.dp))
        Button({navController.navigate(NavControllerRoutes.MAIN_SCREEN.name)}) {
            Text("Play Again")
        }
    }

}