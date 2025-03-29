package com.test.simplegame.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.simplegame.AppPrefs
import com.test.simplegame.MainActivity
import com.test.simplegame.NavControllerRoutes
import com.test.simplegame.R

@Composable
fun MainScreen(innerPadding: PaddingValues, navController: NavHostController,activity: Activity,latestPlayerScore:(Int)-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val interactionSource = remember {  MutableInteractionSource()}
        var currentScore by remember { mutableIntStateOf(0) }
        var textFieldState by remember {
            mutableStateOf(
                "${AppPrefs.getNameOfLatestPlayer()}"
            )
        }
        AppPrefs.setNameOfLatestPlayer(textFieldState)
        Text("Name")
        TextField(value = textFieldState, onValueChange = {
            textFieldState = it
        })
        Spacer(Modifier.size(50.dp))
        Text("Score")
        Box(
            Modifier
                .width(200.dp)
                .height(60.dp)
                .background(Color.Magenta, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("$currentScore", fontSize = 20.sp)
        }
        Text("Highest Score: ${AppPrefs.getScoreOfBestPlayer()}")
        Spacer(Modifier.size(50.dp))
        Box(
            Modifier
                .size(90.dp)
                .background(Color.Red, shape = CircleShape)
                .clickable(
                    onClick = { currentScore++ },
                    interactionSource = interactionSource,
                    indication = ripple(bounded = true, radius = 45.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(R.drawable.baseline_add_24), contentDescription = "")
        }
        Spacer(Modifier.size(50.dp))
        Button(
            {
                if (textFieldState==""){
                    Toast.makeText(activity,"TextField Cannot be empty!!!", Toast.LENGTH_SHORT).show()
                }else{
                    latestPlayerScore(currentScore)
                    if (currentScore > AppPrefs.getScoreOfBestPlayer()) {
                        AppPrefs.setScoreOfBestPlayer(currentScore)
                        AppPrefs.setNameOfBestPlayer(AppPrefs.getNameOfLatestPlayer().toString())
                    }
                    var tempCounterNum = AppPrefs.getNumberOfCounter()
                    tempCounterNum++
                    AppPrefs.setNumberOfCounter(tempCounterNum)
                    navController.navigate(NavControllerRoutes.RESULT_SCREEN.name)
                }
            }, modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        ) {
            Text("Game Over", fontSize = 15.sp)
        }
    }
}