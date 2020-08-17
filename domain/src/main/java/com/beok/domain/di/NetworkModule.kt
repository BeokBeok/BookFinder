package com.beok.domain.di

import com.beok.domain.BooksService
import com.beok.domain.BuildConfig
import com.beok.domain.interceptor.GoogleBooksInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory() = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(jsonAdapter: KotlinJsonAdapterFactory): Moshi =
        Moshi.Builder().add(jsonAdapter).build()

    @Provides
    @Singleton
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        googleBooksInterceptor: GoogleBooksInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(googleBooksInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: MoshiConverterFactory, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideBooksService(retrofit: Retrofit): BooksService =
        retrofit.create(BooksService::class.java)

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/"
    }
}
