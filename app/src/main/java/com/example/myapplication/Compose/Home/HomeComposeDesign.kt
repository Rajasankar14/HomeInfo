package com.example.myapplication.Compose.Home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.onSurfaceDark
import com.example.compose.primaryContainerDark
import com.example.myapplication.Compose.MyUtilities.MyUtilItem
import com.example.myapplication.Compose.MyUtilities.MyUtilitiesBO
import com.example.myapplication.Compose.NearExpiryProduct.NearExpiryItem
import com.example.myapplication.Compose.NearExpiryProduct.NearExpiryProductBO
import com.example.myapplication.Compose.NewPurchase.NewPurchaseActivity
import com.example.myapplication.MainNavigation.MainNaviagationActivity
import com.example.myapplication.R
import com.example.myapplication.newPurchase.NewPurchaseProduct

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeComposeDesign(
    openDrawer: () -> Unit,
    homeScreenViewModel: HomeScreenViewModel
) {
     val materialWhite = Color(0xFFF7F6F4)
    Scaffold(
        topBar = {
            val bodyLarge = TextStyle(fontSize = 18.sp)
            TopAppBar(
                title = { Text("Home", color = Color.White, style = bodyLarge) },
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
   // val pList by rememberUpdatedState(newValue = homeScreenViewModel.prchaseListData1.observeAsState())
    val pList by rememberUpdatedState(newValue = homeScreenViewModel.items.collectAsState())
   // val pList by homeScreenViewModel.prchaseListData1.observeAsState(emptyList())
    LaunchedEffect(key1 = true) {
        homeScreenViewModel.getAllPurchaseProducts()
    }

    // UI
    LazyColumn(
        modifier = Modifier
            .padding(top = 70.dp, start = 12.dp, end = 12.dp, bottom = 20.dp)
    ) {

        item {
            Text(
                text = "Quick Home Info" +
                        "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }
        item {

            MyUtilView(
                utilsList = listOf(
                    MyUtilitiesBO("My Info", R.drawable.myinfo),
                    MyUtilitiesBO("My Visited Stores", R.drawable.mystores),
                    MyUtilitiesBO("Settled Bills", R.drawable.seetbills),
                    MyUtilitiesBO("Pending Bills", R.drawable.settledbills),
                    MyUtilitiesBO("Overall Expenses", R.drawable.expenses),
                )
            )
        }

        item {
            SetMonthlyTarget(homeScreenViewModel)
        }

        item {
            Text(
                text = "Recent Purchased Products",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        /*pList.value?.let {
            items(it.size) { purchaseItem ->
                // List item composable
                ViewPurchaseCard(it[purchaseItem])
            }
        }*/

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
fun WelcomeNotes() {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //  modifier = Modifier.padding(top = 8.dp, start = 15.dp, end = 8.dp)){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)

        ) {
            Text(
                text = "My Home Updates",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )

            Text(
                text = "Overall Purchases and Product usage",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                color = Color.Black
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.2f)
                .size(52.dp)
                .padding(4.dp)
        ) {
            Image(
                painterResource(R.drawable.logo_hd),
                contentDescription = "dadad",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ChipSection(list: List<String>) {


    val materialBlue700 = primaryContainerDark
    val materialSeconaryColor = Color(0xFF089251)
    var selectedChipIndex = 0

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            top = 5.dp,
            bottom = 0.dp,
            start = 15.dp,
            end = 0.dp
        )

    ) {
        Text(
            text = "Top Purchased Category",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = materialBlue700,
        )
        LazyRow {
            items(list.size) { index ->
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = 0.dp,
                            end = 8.dp
                        )
                        .clickable {
                            selectedChipIndex = index
                        }
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (selectedChipIndex == index) materialSeconaryColor
                            else
                                materialBlue700
                        )
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp,
                            start = 20.dp,
                            end = 20.dp
                        )) {

                    Text(text = list[index], color = Color.White)
                }
            }
        }
    }

}

@Composable
fun SetMonthlyTarget(homeScreenViewModel: HomeScreenViewModel) {
    val materialBlue700 = Color(0xFF042D6B)
    val materialSeconaryColor = Color(0xFF089251)
    val context = LocalContext.current


    val totalPurchasesCount by homeScreenViewModel.totalPurchasesCount.observeAsState()
    LaunchedEffect(key1 = Unit) {
        Toast.makeText(context, "Home Data is Loading!", Toast.LENGTH_LONG).show()
        homeScreenViewModel.getTotalPurchaseCount()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 0.dp, bottom = 8.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(15.dp)
            .fillMaxWidth()
    ) {


        Column {
            Text(
                text = "Monthly Analysis Details",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Total Purchases : ${totalPurchasesCount?.totalPurchaseQty}",
                modifier = Modifier.padding(top = 5.dp),
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = "Total Amount : ${totalPurchasesCount?.totalPurchaseAmount}",
                modifier = Modifier.padding(top = 5.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(10.dp)
        ) {


            Image(
                painterResource(R.drawable.analytics),
                contentDescription = "dadad",
                contentScale = ContentScale.Fit
            )


        }

    }

}

@Composable
fun NearExpiryProducts(courses: List<NearExpiryProductBO>) {
    val nearExpiryColor = Color(0xFF042D6B)
    Column(
        modifier = Modifier.padding(
            top = 5.dp,
            bottom = 0.dp,
            start = 15.dp,
            end = 0.dp
        )
    ) {

        Text(
            text = "Near Expiry Products",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = nearExpiryColor
        )
    }

    LazyVerticalGrid(
        userScrollEnabled = false,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(courses.size) {
            NearExpiryItem(course = courses[it])
        }
    }


}


@Composable
fun MyUtilView(utilsList: List<MyUtilitiesBO>) {
    Column(
        modifier = Modifier
            .padding(
                top = 0.dp,
                bottom = 0.dp,
                start = 0.dp,
                end = 0.dp
            )
            .wrapContentSize(align = Alignment.TopStart)
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .heightIn(min = 1.dp)
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                modifier = Modifier
                    .height(100.dp),
            ) {
                items(utilsList.size) {
                    MyUtilItem(course = utilsList[it])
                }

            }
        }
    }


}

@Composable
fun AddFab() {
    val context = LocalContext.current
    val materialBlue700 = primaryContainerDark
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
private fun ViewPurchaseList(viewPurchase: List<NewPurchaseProduct>) {
    LazyColumn {
        items(viewPurchase.size) {
            ViewPurchaseCard(viewPurchase[it])
        }
    }
}


@Composable
private fun ViewPurchaseCard(viewPurchase: NewPurchaseProduct) {
    val materialSeconaryColor = Color(0xFF089251)
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
private fun HomeComposeDesignPreview(){
    // HomeComposeDesign(openDrawer = {}, homeScreenViewModel = HomeScreenViewModel(application = application))

    val mockViewModel = HomeScreenViewModel(application = Application()) // Replace with appropriate mock
    HomeComposeDesign(openDrawer = {}, homeScreenViewModel = mockViewModel)
}









