package com.example.myapplication.Compose.NearExpiryProduct

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class NearExpiryProductBO(val productName: String, val expiryDate : String, val price : String,
                               @DrawableRes val iconId: Int,
                               val lightColor: Color,
                               val mediumColor: Color, val darkColor: Color
)
