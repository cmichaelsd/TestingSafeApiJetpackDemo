package com.colemichaels.jetpackdemo.api

import com.colemichaels.jetpackdemo.api.interceptor.MyInterceptor
import com.colemichaels.jetpackdemo.api.interceptor.MyNetworkInterceptor
import com.colemichaels.jetpackdemo.api.jsonParser.MyMoshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        connectTimeout(DURATION, TimeUnit.SECONDS)
        readTimeout(DURATION, TimeUnit.SECONDS)
        addInterceptor(MyInterceptor())
        addInterceptor(MyNetworkInterceptor.httpLoggingInterceptor)
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MyMoshi.factory)
            .build()
    }

    val api: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://www.giantbomb.com/"
        private const val DURATION = 10L
    }
}