package com.example.carnotassignment.di

import android.content.Context
import com.example.carnotassignment.MyApplication
import com.example.carnotassignment.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val context: Context? = MyApplication.appContext
    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = context?.cacheDir?.let { Cache(it, cacheSize) }

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient().newBuilder().readTimeout(120, TimeUnit.SECONDS)
        .cache(myCache)
        .connectTimeout(120, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val builder: Request.Builder = originalRequest.newBuilder()
                .header("Cache-Control", "public, max-age=" + 180)
                .header("Connection", "keep-alive")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
            logging
            val newRequest = builder.build()
            //println(newRequest.url)
            chain.proceed(newRequest)
        }.build()


    @Provides
    @Singleton
    fun getBaseUrl(): String = "https://api.data.gov.in/resource/"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create()) //important
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}