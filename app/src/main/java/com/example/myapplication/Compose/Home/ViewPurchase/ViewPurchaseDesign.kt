package com.example.myapplication.Compose.Home.ViewPurchase

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.onSurfaceDark
import com.example.myapplication.Compose.Home.HomeScreenViewModel
import com.example.myapplication.Compose.NewPurchase.NewPurchaseActivity
import com.example.myapplication.newPurchase.NewPurchaseProduct


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewPurchaseDesign(
    openDrawer: () -> Unit,
    homeScreenViewModel: HomeScreenViewModel
) {
    val materialWhite = Color(0xFFF7F6F4)
    Scaffold(
        topBar = {
            val bodyLarge = TextStyle(fontSize = 18.sp)
            TopAppBar(
                title = { Text("View Purchased Items", color = Color.White, style = bodyLarge) },
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = materialWhite
                        )
                    }
                }
            )
        },
        content = {
            PurchaseListDesign(homeScreenViewModel)

        },
        floatingActionButton = {
            AddFab()
        },
        floatingActionButtonPosition = FabPosition.End
    )
    // }

}


@Composable
fun PurchaseListDesign(homeScreenViewModel: HomeScreenViewModel) {
    val pList by rememberUpdatedState(newValue = homeScreenViewModel.items.collectAsState())
    LaunchedEffect(key1 = true) {
        homeScreenViewModel.getAllPurchaseProducts()
    }

    // UI
    LazyColumn(
        modifier = Modifier
            .padding(top = 70.dp, start = 12.dp, end = 12.dp, bottom = 20.dp)
    ) {

        

        if (pList != null) {
            pList.let {
                it.value?.let { it1 ->
                    items(it1.size) { purchaseItem ->
                        ViewPurchaseCard(it1[purchaseItem])
                    }
                }
            }
        } else {
            item {
                Text(text = "No recent purchases available.")
            }
        }
    }

}

@Composable
fun AddFab() {
    val context = LocalContext.current
    val materialSeconaryColor = onSurfaceDark
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)

    ) {
        FloatingActionButton(
            onClick = {
                context.startActivity(Intent(context, NewPurchaseActivity::class.java))

            },
            backgroundColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Filled.Add, "", tint = materialSeconaryColor)
        }
    }
}

@Composable
private fun ViewPurchaseCard(viewPurchase: NewPurchaseProduct) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp) // Adjust corner radius as needed
    ) {

        Column(
            modifier = Modifier
                // Change Color.Red to your desired background color
                .padding(8.dp)
                .fillMaxWidth() // Adjust padding as needed
        ) {
            Text(
                text = viewPurchase.productName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = viewPurchase.purchaseDate,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}


@Preview
@Composable
private fun ViewPurchaseDesignPreview(){
    val mockViewModel = HomeScreenViewModel(application = Application()) // Replace with appropriate mock
    ViewPurchaseDesign(openDrawer = {}, homeScreenViewModel = mockViewModel)
}









