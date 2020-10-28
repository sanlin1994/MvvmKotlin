package com.sanlin.myarchitecture.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val  BASE_URL = "https://newsapi.org/v2/"
private const val API_KEY = "Enter your key"

object RetrofitClient {
    fun getClient(): ApiService{

        val requestInterceptor = Interceptor{
                chain ->
            val url:HttpUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("apiKey",API_KEY)
                .addQueryParameter("country","us")
                .build()

            val request:Request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient:OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

}