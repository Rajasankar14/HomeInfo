package com.example.myapplication.Splash.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.compose.AppTheme
import com.example.myapplication.MainNavigation.MainNaviagationActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SPlashScreenActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme {
                SplashScreenDesign()
            }
        }

        lifecycleScope.launch {
            delay(4000)
            withContext(Dispatchers.Main) {
                callHomeScreen()
            }
        }
    }

    private fun callHomeScreen(){
        val intent = Intent(this, MainNaviagationActivity::class.java)
        startActivity(intent)
        finish()
    }
}