package com.keyinc.vk_assignment.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.vk_assignment.R
import com.keyinc.vk_assignment.presentation.ui.component.ProductCard
import com.keyinc.vk_assignment.presentation.ui.state.ProductPaginationState
import com.keyinc.vk_assignment.presentation.viewmodel.ProductViewModel


@Composable
fun ProductScreen(productViewModel: ProductViewModel) {

    val productState by productViewModel.productPaginationState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()


    ProductContent(
        productState = productState,
        productViewModel = productViewModel,
        scrollState = scrollState
    )


}


@Composable
fun ProductError(productViewModel: ProductViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.network_exception_message))
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = { productViewModel.loadNextProducts() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}


@Composable
fun ProductContent(
    productState: ProductPaginationState,
    productViewModel: ProductViewModel,
    scrollState: LazyListState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(productState.items.size) { classroomIndex ->
            LaunchedEffect(scrollState) {
                if (classroomIndex >= productState.items.size - 1
                    && !productState.isEndReached
                    && !productState.isLoading
                ) {
                    productViewModel.loadNextProducts()
                }
            }

            val product = productState.items[classroomIndex]
            ProductCard(
                imageUrl = product.thumbnail,
                title = product.title,
                description = product.description,
            )
        }

        item {
            if (productState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        item {
            if (productState.errorMessage != null) {
                ProductError(productViewModel)
            }
        }
    }
}