package com.keyinc.vk_assignment.domain.entity


import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("products")
    val products: List<Product>
)