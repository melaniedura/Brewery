package com.melaniedura.brewery.di

import com.melaniedura.brewery.network.AuthInterceptor
import com.melaniedura.brewery.network.ResponseHandler
import com.melaniedura.brewery.network.service.BreweryApi
import com.melaniedura.brewery.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideOkHttp(authInterceptor: AuthInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBreweryApi(retrofit: Retrofit): BreweryApi = retrofit.create(BreweryApi::class.java)

    @Provides
    fun provideResponseHandler() = ResponseHandler()
}
