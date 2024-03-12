package com.keyinc.vk_assignment.presentation.ui.state

import com.keyinc.vk_assignment.domain.entity.Product
import com.keyinc.vk_assignment.presentation.exception.ExceptionEnum

data class ProductPaginationState(
    val isLoading: Boolean = false,
    val items: List<Product> = emptyList(),
    var errorMessage: ExceptionEnum? = null,
    val isEndReached: Boolean = false,
    val currentPage: Int = 1
)
