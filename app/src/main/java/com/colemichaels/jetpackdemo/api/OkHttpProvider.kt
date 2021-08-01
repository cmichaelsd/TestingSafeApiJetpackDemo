package com.colemichaels.jetpackdemo.api

import com.colemichaels.jetpackdemo.api.interceptor.MyInterceptor
import com.colemichaels.jetpackdemo.api.interceptor.MyNetworkInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpProvider {
    private const val DURATION = 10L
    var instance: OkHttpClient? = null
    fun getOkHttpClient(): OkHttpClient {
        if (instance == null) {
            instance = OkHttpClient.Builder().apply {
                connectTimeout(DURATION, TimeUnit.SECONDS)
                readTimeout(DURATION, TimeUnit.SECONDS)
                addInterceptor(MyInterceptor())
                addInterceptor(MyNetworkInterceptor.httpLoggingInterceptor)
            }.build()
        }
        return instance!!
    }
}