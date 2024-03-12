package com.keyinc.vk_assignment.presentation.ui.state

import com.keyinc.vk_assignment.domain.entity.Product
import com.keyinc.vk_assignment.domain.entity.ProductList
import com.keyinc.vk_assignment.presentation.exception.ExceptionEnum

sealed class ProductState {
    data object Initial : ProductState()
    data object Loading : ProductState()
    data class Content(val productList: ProductList) : ProductState()
    data class Error(val message: ExceptionEnum) : ProductState()
}