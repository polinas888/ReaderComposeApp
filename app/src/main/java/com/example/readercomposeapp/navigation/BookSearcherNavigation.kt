package com.example.readercomposeapp.navigation

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readercomposeapp.screens.details.BookDetailsScreen
import com.example.readercomposeapp.screens.home.HomeScreen
import com.example.readercomposeapp.screens.login.LoginScreen
import com.example.readercomposeapp.screens.search.BookSearchScreen
import com.example.readercomposeapp.screens.stats.StatsScreen
import com.example.readercomposeapp.screens.update.BookUpdateScreen

@Composable
fun BookSearcherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BookSearcherScreens.SplashScreen.name) {
        composable(BookSearcherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(BookSearcherScreens.HomeScreen.name) {
            HomeScreen(navController=navController)
        }
        composable(BookSearcherScreens.BookDetailScreen.name) {
            BookDetailsScreen(navController=navController)
        }
        composable(BookSearcherScreens.BookUpdateScreen.name) {
            BookUpdateScreen(navController=navController)
        }
        composable(BookSearcherScreens.LoginScreen.name) {
            LoginScreen(navController=navController)
        }
        composable(BookSearcherScreens.StatsScreen.name) {
            StatsScreen(navController=navController)
        }
        composable(BookSearcherScreens.BookSearchScreen.name) {
            BookSearchScreen(navController=navController)
        }
    }
}