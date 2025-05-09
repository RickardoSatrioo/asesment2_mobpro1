package com.rickardosatrioabout.asesment2_mobpro1.navigation

sealed class Screen ( val route: String){
    data object Home: Screen("mainScreen")
}