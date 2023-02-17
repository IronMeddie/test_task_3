package com.example.test_task_3.presentation.Routes

sealed class Route(val route: String){
    object Main : Route("mainListRoute")
    object Details : Route("detailsRoute")
}
