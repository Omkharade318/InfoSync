package com.example.infosync

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.infosync.domain.useCases.AppEntryUseCases
import com.example.infosync.presentation.onboarding.OnBoardingScreen
import com.example.infosync.presentation.onboarding.OnBoardingViewModel
import com.example.infosync.ui.theme.InfoSyncTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()


        enableEdgeToEdge()
        setContent {
            InfoSyncTheme {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event =  viewModel :: onEvent
                )
            }
        }
    }
}