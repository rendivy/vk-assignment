package com.keyinc.vk_assignment.di

import com.keyinc.vk_assignment.data.api.ProductService
import com.keyinc.vk_assignment.data.repository.ProductRepositoryImpl
import com.keyinc.vk_assignment.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService = productService)
    }

}