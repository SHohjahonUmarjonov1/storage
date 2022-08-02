package com.example.datastorage

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {
    private val interceptor=HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client=OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit= Retrofit.Builder()
        .baseUrl("https://dc25fea5-f7a6-41f2-8aa9-0e7dae41f518.mock.pstmn.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val socialApi= retrofit.create(SocialApi::class.java)
}