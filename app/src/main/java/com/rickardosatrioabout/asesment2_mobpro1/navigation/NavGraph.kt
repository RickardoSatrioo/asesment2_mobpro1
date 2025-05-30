package com.rickardosatrioabout.asesment2_mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickardosatrioabout.asesment2_mobpro1.ui.screen.DetailScreen
import com.rickardosatrioabout.asesment2_mobpro1.ui.screen.MainScreen
import com.rickardosatrioabout.asesment2_mobpro1.ui.screen.RecycleScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.FormBaru.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            DetailScreen(navController, id)
        }
        composable(route = Screen.Recycle.route) {
            RecycleScreen(navController)
        }
    }
}
