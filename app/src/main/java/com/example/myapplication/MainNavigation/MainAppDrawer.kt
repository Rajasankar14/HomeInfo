package com.example.myapplication.MainNavigation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.myapplication.R

@Composable
fun AppDrawer(
    navigateToHomeScreen: () -> Unit,
    navigateToNewProductScreen: () -> Unit,
    navigateToViewProductScreen : () -> Unit,
    closeDrawer: () -> Unit
) {

    ModalDrawerSheet {
        NavigationHeader()
        NavigationList(navigateToHomeScreen, navigateToNewProductScreen, navigateToViewProductScreen, closeDrawer)
    }
}


@Composable
fun NavigationList(
    navigateToHomeScreen: () -> Unit,
    navigateToNewProductScreen: () -> Unit,
    navigateToViewProductScreen: () -> Unit,
    closeDrawer: () -> Unit
) {


    NavigationDrawerItem(
        label = { Text(stringResource(id = R.string.menu_home)) },
        selected = true,
        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
        onClick = {
            navigateToHomeScreen(); closeDrawer()
                  },
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 2.dp)
            .height(40.dp))
    Spacer(Modifier.height(4.dp))
    NavigationDrawerItem(label = { Text(stringResource(id = R.string.view_purchase)) },
        selected = true,
        icon = { Icon(Icons.Filled.Info, contentDescription = null) },
        onClick = {navigateToViewProductScreen(); closeDrawer()},
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 2.dp)
            .height(40.dp))
    Spacer(Modifier.height(4.dp))
    NavigationDrawerItem(label = { Text(stringResource(id = R.string.menu_new_purchase_history)) },
        selected = true,
        icon = { Icon(Icons.Filled.Add, contentDescription = null) },
        onClick = { navigateToNewProductScreen(); closeDrawer() },
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 2.dp)
            .height(40.dp)
    )
}

@Composable
fun NavigationHeader() {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)

        ) {
            Text(
                text = "Raja S A",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
        }

        Image(
            painterResource(R.drawable.test_men),
            contentDescription = "dadad",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .wrapContentHeight()
                .size(80.dp)
                .align(alignment = Alignment.CenterVertically)


        )
    }
}


@Preview("Drawer")
@Preview("Drawer Dark View", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    AppTheme {
        AppDrawer(
            navigateToHomeScreen = {},
            navigateToNewProductScreen = {},
            navigateToViewProductScreen = {},
            closeDrawer = {}
        )
    }
}