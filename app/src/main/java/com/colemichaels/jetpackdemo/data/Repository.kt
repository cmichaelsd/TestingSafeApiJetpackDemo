package com.colemichaels.jetpackdemo.data

import com.colemichaels.jetpackdemo.api.RetrofitService
import com.colemichaels.jetpackdemo.api.SafeApiCaller
import com.colemichaels.jetpackdemo.api.response.APIResponse
import com.colemichaels.jetpackdemo.api.response.ResponseWrapper
import com.colemichaels.jetpackdemo.models.Game
import kotlinx.coroutines.CoroutineDispatcher

class Repository(
    private val service: RetrofitService,
    private val dispatcher: CoroutineDispatcher,
    private val apiCaller: SafeApiCaller
) {

    suspend fun getGames(filter: String, limit: Int): ResponseWrapper<APIResponse<List<Game>>> {
        return apiCaller.safeApiCall(dispatcher) { service.getGames(filter, limit) }
    }
}