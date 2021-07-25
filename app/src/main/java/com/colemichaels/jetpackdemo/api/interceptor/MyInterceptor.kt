package com.colemichaels.jetpackdemo.api.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(API_KEY, API_TOKEN)
            .addQueryParameter(FORMAT_KEY, FORMAT_VALUE)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val FORMAT_KEY = "format"
        private const val FORMAT_VALUE = "json"
        private const val API_KEY = "api_key"
        private const val API_TOKEN = ""
    }
}