package com.example.gallery

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {

    lateinit var photoApi: ApiInterface

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit(){

        val jsonConfig = Json {
            ignoreUnknownKeys = true
        }

        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gallery.prod2.webant.ru/")
            .client(okHttpClient)
            .addConverterFactory(jsonConfig.asConverterFactory("application/ld+json".toMediaType()))
            .build()

        photoApi = retrofit.create(ApiInterface::class.java)
    }
}