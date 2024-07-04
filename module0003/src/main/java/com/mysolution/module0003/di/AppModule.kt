package com.mysolution.module0003.di

import com.mysolution.module0003.network.ApiService
import com.mysolution.module0003.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/7/3
 * Describe:
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://qq.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }
}
