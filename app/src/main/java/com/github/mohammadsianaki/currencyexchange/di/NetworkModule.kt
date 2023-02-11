package com.github.mohammadsianaki.currencyexchange.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.github.mohammadsianaki.currencyexchange.data.remote.api.CurrencyRateApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(
        okHttpClient: dagger.Lazy<OkHttpClient>, moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.get())
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun provideAuthInterceptorOkHttpClient(
        @LoggingInterceptorQualifier loggingInterceptorQualifier: Interceptor,
        @ChuckerInterceptorQualifier chuckerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptorQualifier)
            .addInterceptor(chuckerInterceptor).callTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS).cache(cache).build()
    }

    @Provides
    @LoggingInterceptorQualifier
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @ChuckerInterceptorQualifier
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, CACHE_DIRECTORY), 50L * 1024L * 1024L // 50 MiB
        )
    }

    @Provides
    fun provideWebService(retrofit: Retrofit): CurrencyRateApi {
        return retrofit.create()
    }

    private const val BASE_URL = "https://developers.paysera.com/tasks/api/"
    private const val CACHE_DIRECTORY = "http-cache"
    private const val TIMEOUT_CONNECT = 30L
    private const val TIMEOUT_READ = 30L
    private const val TIMEOUT_WRITE = 30L
}