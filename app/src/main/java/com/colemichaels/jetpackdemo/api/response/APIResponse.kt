package com.colemichaels.jetpackdemo.api.response

import com.squareup.moshi.Json

data class APIResponse<T>(
    @Json(name = "results")
    val mResults: T,
    @Json(name = "number_of_page_results")
    val mNumberOfPageResults: Int = 0,
    @Json(name = "number_of_total_results")
    val mNumberOfTotalResults: Int = 0
) {
    val results get() = mResults
    val numberOfPageResults get() = mNumberOfPageResults
    val numberOfTotalResults get() = mNumberOfTotalResults
    val canFetchMore get() = mNumberOfPageResults < mNumberOfTotalResults
}
