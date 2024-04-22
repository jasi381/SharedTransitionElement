package com.jasmeet.sharedtransitionelement.presentation.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jasmeet.sharedtransitionelement.LoremIpsum
import com.jasmeet.sharedtransitionelement.presentation.viewModel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedContentScope,
    onItemClicked: (String) -> Unit,
    vm: MainViewModel = hiltViewModel()
) {

    val response = vm.response.collectAsState()

    LazyColumn(
        modifier = modifier.statusBarsPadding().navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(all = 12.dp),
    ) {

        items(response.value) { url ->

            ElevatedCard(
                onClick = {
                    onItemClicked(url!!.download_url)
                }
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    AsyncImage(
                        model = url?.download_url,
                        modifier = Modifier
                            .size(100.dp)
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = "image-${url?.download_url}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    LoremIpsum(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(
                                    key = "text-$url"
                                ),
                                animatedVisibilityScope,
                            ),
                        maxLines = 3,
                    )

                }
            }

        }

    }

}