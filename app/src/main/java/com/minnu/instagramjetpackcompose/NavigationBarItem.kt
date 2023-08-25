package com.minnu.instagramjetpackcompose

data class BottomNavItem(
    val title: String, val  icon: Int, val route: String
)

enum class BottomNavRoutes(val route: String) {
    HOME("home"), SEARCH("search"), REELS("reels"), NOTIFICATIONS("notification"), PROFILE("profile")
}
