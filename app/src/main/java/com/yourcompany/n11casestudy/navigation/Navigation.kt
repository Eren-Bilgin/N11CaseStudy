package com.yourcompany.n11casestudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yourcompany.n11casestudy.screens.home.HomeScreen
import com.yourcompany.n11casestudy.screens.profile.ProfileScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(navigate = { profileName -> navController.navigate("${Screens.ProfileScreen.route}/$profileName") })
        }
        composable(
            route = "${Screens.ProfileScreen.route}/{profileName}",
            arguments = listOf(navArgument("profileName") { type = NavType.StringType })
        ) {
            ProfileScreen(profileName = it.arguments?.getString("profileName"),
                onNavigateBack = { navController.popBackStack() })
        }
    }
}