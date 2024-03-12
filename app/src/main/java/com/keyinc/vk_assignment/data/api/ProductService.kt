package com.keyinc.vk_assignment.data.api

import com.keyinc.vk_assignment.BuildConfig
import com.keyinc.vk_assignment.di.NetworkConstant.BASE_URL
import com.keyinc.vk_assignment.domain.entity.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET(BuildConfig.PRODUCT_API)
    suspend fun getProducts(
        @Query("skip") skipParameter: String,
        @Query("limit") limitParameter: String
    ): ProductList
}