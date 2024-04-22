package com.jasmeet.sharedtransitionelement.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jasmeet.sharedtransitionelement.LoremIpsum


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    url: String?,
    animatedVisibilityScope: AnimatedContentScope,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .sharedElement(
                    rememberSharedContentState(key = "image-$url"),
                    animatedVisibilityScope,
                ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        LoremIpsum(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .sharedBounds(
                    rememberSharedContentState(
                        key = "text-$url"
                    ),
                    animatedVisibilityScope,
                ),
        )
    }


}
