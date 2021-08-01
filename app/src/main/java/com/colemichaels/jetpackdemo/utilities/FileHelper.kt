package com.colemichaels.jetpackdemo.utilities

import android.content.Context

object FileHelper {
    fun getTextFromResources(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId).use { inputStream ->
            inputStream.bufferedReader().use { bufferedReader ->
                bufferedReader.readText()
            }
        }
    }
}