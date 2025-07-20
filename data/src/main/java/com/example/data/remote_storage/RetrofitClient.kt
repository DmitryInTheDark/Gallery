package com.example.data.remote_storage

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(context: Context){

    @Volatile
    var retrofitInstance: Retrofit? = null

    companion object{
        //константы
        const val SHARED_PREFERENCES_NAME = "TokenSharedPreferences"
        const val TOKEN_NAME = "AuthorizationToken"
        const val REFRESH_TOKEN_NAME = "RefreshToken"
        const val BASE_URL = "https://gallery.prod2.webant.ru/"
    }

    val sharedPreferences = context.applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun configureRetrofit(): ApiInterface{

        val jsonConfiguration = Json{
            ignoreUnknownKeys = true
        }

        var retrofit = retrofitInstance

        if (retrofit == null){
            synchronized(this){
                val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(jsonConfiguration.asConverterFactory("application/json".toMediaType()))
                    .build()

                retrofitInstance = retrofit
            }
            return retrofit!!.create(ApiInterface::class.java)
        }else{
            synchronized(this){
                val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer ${sharedPreferences.getString(TOKEN_NAME, "Default")}").build()
                        return@addInterceptor chain.proceed(request)
                    }
                    .addInterceptor(loggingInterceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(jsonConfiguration.asConverterFactory("application/ld+json".toMediaType()))
                    .build()

                retrofitInstance = retrofit
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }

    fun configureRetrofitForRegistration(): ApiInterface{

        val jsonConfiguration = Json{
            ignoreUnknownKeys = true
        }

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConfiguration.asConverterFactory("application/ld+json".toMediaType()))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    fun configureRetrofitForAuthorization(): ApiInterface{
        val jsonConfiguration = Json{
            ignoreUnknownKeys = true
        }

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConfiguration.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}