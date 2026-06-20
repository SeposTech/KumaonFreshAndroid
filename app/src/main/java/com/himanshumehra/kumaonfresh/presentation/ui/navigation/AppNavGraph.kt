package com.himanshumehra.kumaonfresh.presentation.ui.navigation

import com.himanshumehra.kumaonfresh.presentation.ui.home.ItemScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.himanshumehra.kumaonfresh.presentation.ui.cart.CartDetailScreen
import com.himanshumehra.kumaonfresh.presentation.ui.login.LoginScreen
import com.himanshumehra.kumaonfresh.presentation.ui.registration.RegistrationPage

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "registration") {
            RegistrationPage(navController)
        }
        composable(route = "item/{catId}", arguments = listOf(navArgument("catId") {
            type = NavType.LongType
        })) { backStackEntry ->
            ItemScreen(
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate("cartDetail") }
            )
        }
        composable(route = "cartDetail") {
            CartDetailScreen(onBackClick = { navController.popBackStack() })
        }
    }
}