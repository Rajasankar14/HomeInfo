package com.example.myapplication.MainNavigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.Compose.Home.HomeComposeDesign
import com.example.myapplication.Compose.Home.HomeScreenViewModel
import com.example.myapplication.Compose.Home.ViewPurchase.ViewPurchaseDesign
import com.example.myapplication.Compose.NewPurchase.NewPurchaseActivity
import com.example.myapplication.Compose.NewPurchase.NewPurchaseDesign
import com.example.myapplication.Splash.UI.SPlashScreenActvity


@Composable
fun NavDrawerGraph(widthSizeClass: WindowWidthSizeClass,
                   isExpandedScreen: Boolean,
                   openDrawer: () -> Unit = {},
                   navController: NavHostController,
                   startDestination: String = NavigationItem.Home.route,
                   homeScreenViewModel : HomeScreenViewModel) {

    NavHost(navController = navController , startDestination = startDestination){
        composable(NavigationItem.Home.route) {

            HomeComposeDesign(openDrawer,homeScreenViewModel)
        }
        composable(NavigationItem.NewPurchase.route) {
            NewPurchaseActivity()
        }
        composable(NavigationItem.Splash.route) {
            SPlashScreenActvity()
        }

        composable(NavigationItem.ViewPurchase.route) {
            ViewPurchaseDesign(openDrawer,homeScreenViewModel)
        }
    }


    }
