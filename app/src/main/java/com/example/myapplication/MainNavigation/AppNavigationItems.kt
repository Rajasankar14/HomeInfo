package com.example.myapplication.MainNavigation

enum class Screen {
    HOME,
    NEW_PURCHASE,
    SPLASH,
    VIEW_PURCHASE
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object NewPurchase : NavigationItem(Screen.NEW_PURCHASE.name)
    object Splash : NavigationItem(Screen.SPLASH.name)
    object ViewPurchase : NavigationItem(Screen.VIEW_PURCHASE.name)
}