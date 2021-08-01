package com.colemichaels.jetpackdemo

import android.app.Application

open class JetpackApp : Application() {
    init {
        Environment.current = Environment.PROD
    }
}