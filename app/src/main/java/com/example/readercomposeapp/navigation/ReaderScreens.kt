package com.example.readercomposeapp.navigation

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    BookSearchScreen,
    StatsScreen,
    BookUpdateScreen,
    BookDetailScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens
          = when(route?.substringBefore("/")) {
              SplashScreen.name -> SplashScreen
              LoginScreen.name -> LoginScreen
              CreateAccountScreen.name -> CreateAccountScreen
              HomeScreen.name -> HomeScreen
              BookSearchScreen.name -> BookSearchScreen
              StatsScreen.name -> StatsScreen
              BookUpdateScreen.name -> BookUpdateScreen
              BookDetailScreen.name -> BookDetailScreen
              null -> HomeScreen
              else -> throw  java.lang.IllegalArgumentException("Route $route isn't recognized")
          }
    }
}