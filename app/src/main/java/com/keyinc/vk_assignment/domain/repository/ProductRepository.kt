package com.keyinc.vk_assignment.domain.repository

import com.keyinc.vk_assignment.domain.entity.ProductList

interface ProductRepository {

    suspend fun getProducts(skip: Int, limit: Int) : ProductList
}