package com.example.coloringkids

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coloringkids.screens.AlphabetScreen
import com.example.coloringkids.screens.AnimalScreen
import com.example.coloringkids.screens.ColoringScreen
import com.example.coloringkids.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Alphabet : Screen("alphabet")
    object Animals : Screen("animals")
    object Coloring : Screen("coloring/{type}/{item}") {
        fun createRoute(type: String, item: String) = "coloring/$type/$item"
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Alphabet.route) {
            AlphabetScreen(navController = navController)
        }
        composable(Screen.Animals.route) {
            AnimalScreen(navController = navController)
        }
        composable(
            route = Screen.Coloring.route,
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("item") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val item = backStackEntry.arguments?.getString("item") ?: ""
            ColoringScreen(
                navController = navController,
                type = type,
                item = item
            )
        }
    }
}
