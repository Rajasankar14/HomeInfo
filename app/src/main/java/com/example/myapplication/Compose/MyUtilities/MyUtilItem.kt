package com.example.myapplication.Compose.MyUtilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.Compose.NearExpiryProduct.NearExpiryProductBO
import com.example.myapplication.R

@Composable
fun MyUtilItem(course: MyUtilitiesBO) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(80.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(Color.White)
                .padding(0.dp)
        ) {


            Image(
                painter = painterResource(id = course.iconId),
                contentDescription = "dadad",
                contentScale = ContentScale.Fit,)


        }
                Text(
                    text = course.utilName,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                            textAlign = TextAlign.Center

                )
    }


}

@Preview
@Composable
fun MyUtilItemPreview(){
    MyUtilItem(MyUtilitiesBO("",R.drawable.mamaearth1))

}

