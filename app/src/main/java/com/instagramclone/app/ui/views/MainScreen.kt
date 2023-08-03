package com.instagramclone.app.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.instagramclone.app.R
import com.instagramclone.app.navigation.NavigationGraph
import com.instagramclone.app.ui.theme.InstagramCloneTheme

sealed class BottomNavItem(var title:String, var selectedIcon: ImageVector, var unSelectedIcon: ImageVector, var screenRoute:String){
    object Home : BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home,"home")
    object Search: BottomNavItem("Search",Icons.Filled.Search, Icons.Outlined.Search,"search")
    object Add: BottomNavItem("Add",Icons.Filled.AddCircle, Icons.Outlined.AddCircle,"add")
    object Reels: BottomNavItem("Reels",Icons.Filled.PlayArrow, Icons.Outlined.PlayArrow,"reels")
    object Account: BottomNavItem("Account",Icons.Filled.AccountBox, Icons.Outlined.AccountBox,"accuont")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    var navController = rememberNavController()
    Scaffold(
        topBar = { InstaTopBar() },
        bottomBar = { InstaBottomNavigation(navController) }
    ) {
        paddingValues ->
        NavigationGraph(navController = navController, paddingValues)
    }
}

@Composable
fun InstaTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp , bottom = 10.dp)
    ){
        //Instagram image on the left the top Bar - You can use any custom image drawable or text here
        Image(painter = painterResource(id = R.drawable.instagramlogo), contentDescription = "Instagram name")
        //Spacer to fill in the space between InstagramLogo and notifications heart icon
        Spacer(modifier = Modifier.weight(1f))
        //Using the Default Favorite Icon as notification icon
        Icon(Icons.Default.FavoriteBorder, contentDescription = "Notifications Icon")
        //Spacer between notification and messages icons
        Spacer(modifier = Modifier.weight(0.1f))
        //Using the Default email Icon as messages icon
        Icon(Icons.Outlined.Email, contentDescription = "Messages Icon")
    }
}

@Composable
fun InstaBottomNavigation(navController: NavController) {
    //Creating a list of 5 Tabs (BottomNavItem)
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Add,
        BottomNavItem.Reels,
        BottomNavItem.Account
    )
    //Navigation Bar
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(50.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        //variable to keep track of the current route
        val currentRoute = navBackStackEntry?.destination?.route
        //iterating over the list of 5 tabs defined above, creating and adding
        // them to the NavigationBar
        items.forEach { item ->
            NavigationBarItem(
                //Using the TabIcon based on if the tab is selected (currentRoute == tabRoute)
                icon = {
                        BottomNavIconComposable(if(currentRoute == item.screenRoute) item.selectedIcon else item.unSelectedIcon, item.title)
                       },
                //Defining what happens when tab is clicked
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavIconComposable(vector : ImageVector, contentDescriptor: String){
    Icon(
        imageVector = vector,
        contentDescription = contentDescriptor
    )
}

@Preview
@Composable
fun MainScreenPreview(){
    InstagramCloneTheme {
        MainScreen()
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    InstagramCloneTheme {
        var navController = rememberNavController()
        InstaBottomNavigation(navController)
    }
}
