package com.example.test_task_3.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test_task_3.presentation.Routes.Route
import com.example.test_task_3.presentation.detailsScreen.Details
import com.example.test_task_3.presentation.detailsScreen.DetailsScreen
import com.example.test_task_3.presentation.listScreen.MainScreen
import com.example.test_task_3.presentation.theme.Test_task_3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Test_task_3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Route.Main.route){
                        MainScreen(navController)
                        Details(navController)
                    }
                }
            }
        }
    }
}

