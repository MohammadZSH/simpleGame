vless://1bf197fe-977f-4c6b-b466-c897bf50a3ce@151.101.3.8:80?path=%2Fdownload%3Fed%3D2560&security=&encryption=none&host=ismc.ir&type=ws#%F0%9F%87%B3%F0%9F%87%B1%20%20%D9%87%D9%84%D9%86%D8%AF%20-%20%D8%A7%DB%8C%D8%B1%D8%A7%D9%86%D8%B3%D9%84%20%F0%9F%94%A5



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
