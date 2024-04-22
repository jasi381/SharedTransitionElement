package com.jasmeet.sharedtransitionelement

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

private val Lorem = """
   Work let's prioritize the low-hanging fruit, effort made was a lot hop on the bandwagon
    powerPointless, so helicopter view streamline. Open door policy optimize the fireball pixel
    pushing, yet thinking outside the box, yet window-licker. Drink from the firehose it's about
    managing expectations, nor run it up the flag pole. We need to leverage our synergies 
    future-proof, nor we have to leverage up the messaging organic growth, if you're not hurting 
    you're not winning. Window of opportunity make it a priority, nor i'll book a meeting so we 
    can solution this before the sprint is over, but we need to button up our approach.
""".trimIndent()

@Composable
fun LoremIpsum(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = Lorem,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}