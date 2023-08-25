package com.minnu.instagramjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.minnu.instagramjetpackcompose.screens.HomeScreen
import com.minnu.instagramjetpackcompose.screens.NotificationScreen
import com.minnu.instagramjetpackcompose.screens.ProfileScreen
import com.minnu.instagramjetpackcompose.screens.ReelsScreen
import com.minnu.instagramjetpackcompose.screens.SearchScreen

@Composable
fun InstagramNavGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = BottomNavRoutes.HOME.route) {
        composable(route = BottomNavRoutes.HOME.route) {
            HomeScreen()
        }
        composable(route = BottomNavRoutes.SEARCH.route) {
            SearchScreen()
        }
        composable(route = BottomNavRoutes.REELS.route) {
            ReelsScreen()
        }
        composable(route = BottomNavRoutes.NOTIFICATIONS.route) {
            NotificationScreen()
        }
        composable(route = BottomNavRoutes.PROFILE.route) {
            ProfileScreen()
        }
    }
}