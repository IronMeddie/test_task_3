package com.example.test_task_3.presentation.listScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.test_task_3.presentation.Routes.Route


fun NavGraphBuilder.MainScreen(navController: NavController){
    composable(Route.Main.route){
        ListScreen(navController)
    }
}

fun NavController.toMainScreen(options: NavOptions? = null){
    this.navigate(Route.Main.route, options)
}