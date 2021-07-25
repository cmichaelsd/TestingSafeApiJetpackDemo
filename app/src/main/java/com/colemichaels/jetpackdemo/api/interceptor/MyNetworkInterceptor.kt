package com.colemichaels.jetpackdemo.api.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object MyNetworkInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}