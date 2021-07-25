package com.colemichaels.jetpackdemo.api.jsonParser

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType

object MyMoshi {
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val factory: MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    inline fun <reified T> createListType(): ParameterizedType =
        Types.newParameterizedType(List::class.java, T::class.java)

    inline fun <reified T> createListAdapter(): JsonAdapter<List<T>> {
        val type = createListType<T>()
        return moshi.adapter(type)
    }

    inline fun <reified T> listToJsonString(model: List<T>): String =
        createListAdapter<T>().toJson(model).toString()

    inline fun <reified T> listFromJsonString(string: String): List<T>? =
        createListAdapter<T>().fromJson(string)
}