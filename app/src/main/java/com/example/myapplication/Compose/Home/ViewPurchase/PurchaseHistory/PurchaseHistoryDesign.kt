package com.example.myapplication.Compose.Home.ViewPurchase.PurchaseHistory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.compose.backgroundDarkHighContrast
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PurchaseHistoryDesign() {

    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )
    Column (modifier =  Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
          Tabs(pagerState = pagerState)

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState : PagerState){
     val tabList = listOf("Today", "Monthly", "Yearly")
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage) {

        tabList.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index,  onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
            enabled = true,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.DarkGray
            , interactionSource = remember {
                    MutableInteractionSource()
                }) {

            }
        }

        
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(pagerState: PagerState){
    HorizontalPager(state = pagerState) {
        page ->
        when(page){
            0 -> {
               // TabContentScreen()
            }
            1 -> {

            }
            2 -> {

            }
        }
        
    }
}

@Composable
fun TabContentScreen(pageContent: String){
    Column {
        Text(text =pageContent, style = MaterialTheme.typography.headlineMedium, color = Color.Black, textAlign = TextAlign.Center )
    }
}