package com.example.infosync.news.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.infosync.news.presentation.news_navigator.NewsNavigator
import com.example.infosync.news.presentation.onboarding.OnBoardingScreen
import com.example.infosync.news.presentation.onboarding.OnBoardingViewModel
import com.example.infosync.chatBot.ChatPage
import com.example.infosync.chatBot.model.ChatViewModel


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
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
                    NewsNavigator()
                }
            }
            
            composable(route = Route.ChatScreen.route) {
                val chatViewModel: ChatViewModel = hiltViewModel()
                Box(modifier = Modifier.fillMaxSize()) {
                    ChatPage(
                        modifier = Modifier,
                        viewModel = chatViewModel
                    )
                }
            }
        }
    }
}
