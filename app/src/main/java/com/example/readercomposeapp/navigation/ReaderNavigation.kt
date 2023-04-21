package com.example.readercomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readercomposeapp.screens.SplashScreen
import com.example.readercomposeapp.screens.details.BookDetailsScreen
import com.example.readercomposeapp.screens.home.HomeScreen
import com.example.readercomposeapp.screens.login.LoginScreen
import com.example.readercomposeapp.screens.search.BookSearchScreen
import com.example.readercomposeapp.screens.stats.StatsScreen
import com.example.readercomposeapp.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(ReaderScreens.HomeScreen.name) {
            HomeScreen(navController=navController)
        }
        composable(ReaderScreens.BookDetailScreen.name) {
            BookDetailsScreen(navController=navController)
        }
        composable(ReaderScreens.BookUpdateScreen.name) {
            BookUpdateScreen(navController=navController)
        }
        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController=navController)
        }
        composable(ReaderScreens.StatsScreen.name) {
            StatsScreen(navController=navController)
        }
        composable(ReaderScreens.BookSearchScreen.name) {
            BookSearchScreen(navController=navController)
        }
    }
}