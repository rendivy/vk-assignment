package com.keyinc.vk_assignment.data.repository

import com.keyinc.vk_assignment.data.api.ProductService
import com.keyinc.vk_assignment.domain.entity.ProductList
import com.keyinc.vk_assignment.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productService: ProductService) :
    ProductRepository {


    override suspend fun getProducts(skip: Int, limit: Int): ProductList {
        return productService.getProducts(skipParameter = skip.toString(), limitParameter = limit.toString())
    }

}