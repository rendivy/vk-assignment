package com.keyinc.vk_assignment.domain.usecase

import com.keyinc.vk_assignment.domain.repository.ProductRepository
import javax.inject.Inject


class GetProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend fun invoke(skip: Int, limit: Int) =
        try {
            Result.success(productRepository.getProducts(skip, limit).products)
        } catch (e: Exception) {
            Result.failure(e)
        }


}