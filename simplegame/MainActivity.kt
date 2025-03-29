package com.test.simplegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.simplegame.screens.MainScreen
import com.test.simplegame.screens.ResultScreen
import com.test.simplegame.ui.theme.SimpleGameTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPrefs.setUpAppPrefs(this)
        enableEdgeToEdge()
        setContent {
            SimpleGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var latestPlayerScore by remember { mutableIntStateOf(0) }
                    val navController = rememberNavController()
                    BackHandler {
                        if (navController.currentBackStackEntry?.destination?.route == NavControllerRoutes.MAIN_SCREEN.name) {
                            this.finish()
                        }else{
                            navController.navigate(NavControllerRoutes.MAIN_SCREEN.name)
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = NavControllerRoutes.MAIN_SCREEN.name
                    ) {
                        composable(route = NavControllerRoutes.MAIN_SCREEN.name) {
                            MainScreen(innerPadding, navController, LocalActivity.current!!) {
                                latestPlayerScore = it
                            }
                        }
                        composable(route = NavControllerRoutes.RESULT_SCREEN.name) {
                            ResultScreen(navController, innerPadding, latestPlayerScore)
                        }
                    }
                }
            }
        }
    }
}
