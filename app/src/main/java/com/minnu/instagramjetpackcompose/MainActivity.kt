package com.minnu.instagramjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.minnu.instagramjetpackcompose.ui.theme.InstagramJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    InstagramApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun InstagramApp() {

    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBarMaterial3(navHostController) }, containerColor = Color.White
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            InstagramNavGraph(navHostController = navHostController)
        }
    }
}

@Composable
fun BottomNavBarMaterial3(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        BottomNavItem("home", R.drawable.home, BottomNavRoutes.HOME.route),
        BottomNavItem("search", R.drawable.search, BottomNavRoutes.SEARCH.route),
        BottomNavItem("reels", R.drawable.reels, BottomNavRoutes.REELS.route),
        BottomNavItem(
            "notification",
            R.drawable.shop,
            BottomNavRoutes.NOTIFICATIONS.route
        ),
        BottomNavItem("profile", R.drawable.profile, BottomNavRoutes.PROFILE.route),
    )

    NavigationBar(containerColor = Color.White) {
        bottomNavItems.forEach { item ->

            val selected = item.route == currentDestination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route)
                },
                /*label = {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.SemiBold,
                    )
                },*/
                icon = {
                    if (item.title.lowercase() == "profile") {
                        CircularProfileView()
                    } else {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = ""
                        )
                    }
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = Color.Red,
                        indicatorColor = Color.White
                    )
            )
        }
    }
}

@Composable
fun CircularProfileView() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "",
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun ProfilePreview() {
    CircularProfileView()
}