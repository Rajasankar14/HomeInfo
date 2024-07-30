package com.example.myapplication.MainNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.compose.AppTheme
import com.example.myapplication.Compose.Home.HomeScreenViewModel
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.model.NewPurchaseViewModel

class MainNaviagationActivity : ComponentActivity(){
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeScreenViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[HomeScreenViewModel::class.java]
        /*homeScreenViewModel.addNewPurchaseProduct(
            NewPurchaseProduct( "Headset", "","","","2","","1000")
        )*/
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
            AppTheme {
                HomeDesign(widthSizeClass, homeScreenViewModel)
            }

        }


    }
}