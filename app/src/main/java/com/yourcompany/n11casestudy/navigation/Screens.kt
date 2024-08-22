package com.yourcompany.n11casestudy.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("Home")
    object ProfileScreen : Screens("Profile")
}