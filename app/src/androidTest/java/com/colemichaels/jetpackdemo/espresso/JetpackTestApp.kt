package com.colemichaels.jetpackdemo.espresso

import com.colemichaels.jetpackdemo.Environment
import com.colemichaels.jetpackdemo.JetpackApp

class JetpackTestApp : JetpackApp() {
    init {
        Environment.current = Environment.MOCK
    }
}