package com.keyinc.vk_assignment.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keyinc.vk_assignment.R
import com.keyinc.vk_assignment.presentation.ui.theme.SubtitleStyle
import com.keyinc.vk_assignment.presentation.ui.theme.TitleStyle
import com.keyinc.vk_assignment.presentation.ui.theme.paddingMedium
import com.keyinc.vk_assignment.presentation.ui.theme.paddingSmall


@Composable
fun ProductCard(
    imageUrl: String = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
    title: String = "Product Title",
    description: String = "Product Description",
    cardPrice: String,
) {

    ElevatedCard(modifier = Modifier.padding(paddingMedium)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageUrl,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(start = paddingSmall),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(id = R.string.card_description),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(bottom = paddingMedium),
                    style = TitleStyle,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$cardPrice USD",
                    modifier = Modifier.padding(bottom = paddingMedium),
                    style = SubtitleStyle,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = description,
                    style = SubtitleStyle,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }


}
