package com.colemichaels.jetpackdemo

object Environment {
    const val PROD = "prod"
    const val MOCK = "mock"

    private const val PROD_URL = "https://www.giantbomb.com/"
    private const val MOCK_URL = "http://127.0.0.1:8080/" // 127.0.0.1 on physical device 10.0.2.2 for emulator

    lateinit var current: String

    fun getBaseUrl(): String {
        return if (current == PROD) PROD_URL
        else MOCK_URL
    }
}