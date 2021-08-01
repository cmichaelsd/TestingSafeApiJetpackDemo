package com.colemichaels.jetpackdemo.api

import com.colemichaels.jetpackdemo.api.response.APIResponse
import com.colemichaels.jetpackdemo.models.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("api/games")
    suspend fun getGames(
        @Query("filter") filter: String,
        @Query("limit") limit: Int
    ): APIResponse<List<Game>>
}