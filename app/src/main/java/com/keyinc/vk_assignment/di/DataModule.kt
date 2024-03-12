package com.keyinc.vk_assignment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.keyinc.vk_assignment.BuildConfig
import com.keyinc.vk_assignment.data.api.ProductService
import com.keyinc.vk_assignment.di.NetworkConstant.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


data object NetworkConstant {
    internal const val CONNECT_TIMEOUT = 5L
    internal const val READ_TIMEOUT = 10L
    internal const val WRITE_TIMEOUT = 10L
    internal const val BASE_URL = "https://dummyjson.com/products/"

}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(NetworkConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstant.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstant.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PRODUCT_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit) : ProductService {
        return retrofit.create(ProductService::class.java)
    }


}