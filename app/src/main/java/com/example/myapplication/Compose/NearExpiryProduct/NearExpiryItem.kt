package com.example.myapplication.Compose.NearExpiryProduct

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(widthDp = 700, heightDp = 1400)
@Composable
fun NearExpiryItem(course: NearExpiryProductBO) {
    Box(
        // Box with some attributes
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(course.darkColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Column {
                Text(
                    text = course.productName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                )

                Text(
                    text = course.price,
                    style = MaterialTheme.typography.body2,
                )

                Text(
                    text = course.expiryDate,
                    style = MaterialTheme.typography.body2,
                    color = Color.Red
                )


                Image(
                    painter = painterResource(id = course.iconId),
                    contentDescription = course.productName,
                    //  modifier = Modifier.align(LineHeightStyle.Alignment.Bottom)
                )
            }


            Text(
                text = "View",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .clickable {
                        // Handle the clicks
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Red)
                    .padding(8.dp)
            )
        }
    }
}