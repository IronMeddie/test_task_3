package com.example.test_task_3.presentation.detailsScreen

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.test_task_3.presentation.Routes.Route

fun NavGraphBuilder.Details(navController: NavController){
    composable(Route.Details.route + "/{id}", arguments =  listOf(
        navArgument(name = "id"){
            type = NavType.IntType
            defaultValue = 0 })){
        DetailsScreen(navController)
    }
}

fun NavController.toDetails(id: Int){
    this.navigate(Route.Details.route + "/${id}")
}