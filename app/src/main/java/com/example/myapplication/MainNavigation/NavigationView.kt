package com.example.myapplication.MainNavigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.myapplication.R
@Composable
fun NavigationView( currentRoute: String,
navigateToHome: () -> Unit,
navigateToInterests: () -> Unit,
modifier: Modifier = Modifier
) {
    NavigationRail(header = {
        Icon(
            painterResource(R.drawable.ic_menu_camera),
            null,
            Modifier.padding(vertical = 12.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    },
        modifier = modifier
    ) {

        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == NavigationItem.Home.route,
            onClick = {},
            icon = { Icon(Icons.Filled.Home, stringResource(R.string.menu_home)) },
            label = { Text(stringResource(R.string.menu_home)) },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == NavigationItem.NewPurchase.route,
            onClick = {  },
            icon = { Icon(Icons.Filled.Info, stringResource(R.string.menu_new_purchase)) },
            label = { Text(stringResource(R.string.menu_new_purchase)) },
            alwaysShowLabel = false
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    AppTheme {
        NavigationView(
            currentRoute = NavigationItem.Home.route,
            navigateToHome = {},
            navigateToInterests = {},
        )
    }
}
