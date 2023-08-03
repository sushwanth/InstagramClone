package com.instagramclone.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instagramclone.app.ui.views.BottomNavItem
import com.instagramclone.app.ui.views.tabScreens.AccountScreen
import com.instagramclone.app.ui.views.tabScreens.AddScreen
import com.instagramclone.app.ui.views.tabScreens.HomeScreen
import com.instagramclone.app.ui.views.tabScreens.ReelsScreen
import com.instagramclone.app.ui.views.tabScreens.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen(paddingValues)
        }
        composable(BottomNavItem.Add.screenRoute) {
            AddScreen()
        }
        composable(BottomNavItem.Reels.screenRoute) {
            ReelsScreen()
        }
        composable(BottomNavItem.Account.screenRoute) {
            AccountScreen()
        }
    }
}