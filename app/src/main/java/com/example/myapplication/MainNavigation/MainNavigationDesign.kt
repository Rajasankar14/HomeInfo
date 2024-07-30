package com.example.myapplication.MainNavigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Compose.Home.HomeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeDesign(widthSizeClass: WindowWidthSizeClass, homeScreenViewModel: HomeScreenViewModel) {

    /*For Navigation Controller*/
    val navController = rememberNavController()

    val navigationActions = remember(navController) {
        NavigationAction(navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    /*For Thread calling*/
    val coroutineScope = rememberCoroutineScope()


    val currentRoute = navBackStackEntry?.destination?.route ?: NavigationItem.Home.route
    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

    val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                navigateToHomeScreen = navigationActions.navigateToHomeScreen,
                navigateToNewProductScreen = navigationActions.navigateToNewProductScreen,
                navigateToViewProductScreen = navigationActions.navigateToViewProductScreen,
                closeDrawer = {
                    coroutineScope.launch { sizeAwareDrawerState.close() }
                }

            )
        },
        drawerState = sizeAwareDrawerState,
        gesturesEnabled = !isExpandedScreen
    ) {
        Row {
            if (isExpandedScreen) {
                NavigationView(
                    currentRoute = currentRoute,
                    navigateToHome = { },
                    navigateToInterests = {},
                )
            }
            NavDrawerGraph(
                widthSizeClass = widthSizeClass,
                isExpandedScreen = isExpandedScreen,
                navController = navController,
                openDrawer = {
                    coroutineScope.launch {
                    sizeAwareDrawerState.open()
                }
                             },
                homeScreenViewModel = homeScreenViewModel
            )
        }


    }
}

@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        drawerState
    } else {
        DrawerState(DrawerValue.Closed)
    }
}


class NavigationAction(navController: NavController) {
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(NavigationItem.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToNewProductScreen: () -> Unit = {
        navController.navigate(NavigationItem.NewPurchase.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true

            }
            launchSingleTop = true
            restoreState = true
        }
    }


    val navigateToViewProductScreen: () -> Unit = {
        navController.navigate(NavigationItem.ViewPurchase.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true

            }
            launchSingleTop = true
            restoreState = true
        }
    }



}
