package com.colemichaels.jetpackdemo.api

import com.colemichaels.jetpackdemo.api.response.ErrorResponse
import com.colemichaels.jetpackdemo.api.response.ResponseWrapper
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SafeApiCaller(private val moshi: Moshi) {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResponseWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResponseWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResponseWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResponseWrapper.GenericError(code, errorResponse)
                    }
                    else -> ResponseWrapper.GenericError(null, null)
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            ErrorResponse(throwable.message())
        } catch (exception: Exception) {
            null
        }
    }
}