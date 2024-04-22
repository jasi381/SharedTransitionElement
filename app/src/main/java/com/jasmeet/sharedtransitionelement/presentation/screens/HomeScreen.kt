package com.jasmeet.sharedtransitionelement.presentation.screens

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLEncoder

const val IMG_URL = "imageUrl"

sealed class Screens(val route: String) {
    data object ListScreen : Screens("listScreen")
    data object DetailsScreen : Screens("detailsScreen/{$IMG_URL}") {
        fun passImageUrl(url: String): String {
            return this.route.replace("{$IMG_URL}", url)
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.ListScreen.route,
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideInHorizontally { it } + fadeIn() },
            exitTransition = { slideOutHorizontally { -it } + fadeOut() },
            popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
            popExitTransition = { slideOutHorizontally { it } + fadeOut() },
        ) {

            composable(
                route = Screens.ListScreen.route,
            ) {
                ListScreen(
                    animatedVisibilityScope = this@composable,
                    onItemClicked = { url ->
                        val encodedUrl = URLEncoder.encode(url, "UTF-8")
                        navController.navigate(Screens.DetailsScreen.passImageUrl(encodedUrl))
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                )
            }
            composable(
                route = Screens.DetailsScreen.route,
                arguments = listOf(
                    navArgument(IMG_URL) {
                        type = NavType.StringType
                        defaultValue = "00"
                        nullable = true
                    }
                )
            ) { backStack ->
                DetailsScreen(
                    url = backStack.arguments?.getString(IMG_URL),
                    animatedVisibilityScope = this@composable,
                    onBackPressed = { navController.popBackStack() }
                )
            }

        }
    }

}