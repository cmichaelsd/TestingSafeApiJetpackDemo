package com.colemichaels.jetpackdemo.api

import com.colemichaels.jetpackdemo.Environment
import com.colemichaels.jetpackdemo.api.jsonParser.MyMoshi
import retrofit2.Retrofit

class RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Environment.getBaseUrl())
            .client(OkHttpProvider.getOkHttpClient())
            .addConverterFactory(MyMoshi.factory)
            .build()
    }

    val api: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}