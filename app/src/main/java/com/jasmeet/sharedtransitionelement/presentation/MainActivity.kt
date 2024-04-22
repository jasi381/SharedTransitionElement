package com.jasmeet.sharedtransitionelement.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jasmeet.sharedtransitionelement.presentation.screens.HomeScreen
import com.jasmeet.sharedtransitionelement.presentation.theme.SharedTransitionElementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedTransitionElementTheme {
                val navController = rememberNavController()
                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController
                    )
            }
        }
    }
}
