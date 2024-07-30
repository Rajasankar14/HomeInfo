package com.example.myapplication.Compose.NewPurchase

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme

class NewPurchaseActivity : ComponentActivity() {

  private lateinit var newPurchaseViewModel :NewPurchaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent{
            AppTheme {
                 newPurchaseViewModel  = viewModel(factory = NewPurchaseViewModelFactory(baseContext,application))
                //val onBackpress = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
                NewPurchaseDesign(onBackPress = {
                    finish()

                }, newPurchaseViewModel)
            }

            lifecycleScope.launchWhenStarted {
                newPurchaseViewModel.triggerFinishNewDesignScreen.collect{
                    if(it){
                        finish()
                        newPurchaseViewModel.resetFinishScreenValue()
                    }
                }
            }

        }
    }
}