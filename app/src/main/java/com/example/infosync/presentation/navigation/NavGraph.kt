package com.example.infosync.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infosync.presentation.home.HomeScreen
import com.example.infosync.presentation.home.HomeViewModel
import com.example.infosync.presentation.onboarding.OnBoardingScreen
import com.example.infosync.presentation.onboarding.OnBoardingViewModel


@Composable
fun NavGraph(
    startDestination: String,
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){

        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNavigation.route
        ){
            composable(route = Route.OnBoardingScreen.route){

                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event =  viewModel :: onEvent
                )

            }
        }

        navigation(
            startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route
        ){
            composable(route = Route.NewsNavigatorScreen.route){
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    val articles = viewModel.news.collectAsLazyPagingItems()

                    HomeScreen(
                        articles = articles,
                        navigate ={

                        }
                    )
                }
            }
        }
    }
}
