package com.georgiopoulos.core.network.di

import com.georgiopoulos.core.network.NetworkConstants
import com.georgiopoulos.core.network.interceptor.HttpRequestInterceptor
import com.georgiopoulos.core.network.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dagger module providing network-related dependencies for the application.
 * This module is installed in the SingletonComponent, indicating that the provided dependencies are available as singletons.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    /**
     * Provides an instance of OkHttpClient with an HttpRequestInterceptor added and
     * a HttpLoggingInterceptor
     *
     * @return an OkHttpClient instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    /**
     * Provides an instance of Retrofit configured with the OkHttpClient, base URL, and MoshiConverterFactory.
     *
     * @param okHttpClient the OkHttpClient instance to be used by Retrofit
     * @return a Retrofit instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * Provides an instance of the Service interface based on the Retrofit instance.
     *
     * @param retrofit the Retrofit instance to be used for creating the Service interface implementation
     * @return a Service instance
     */
    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}
