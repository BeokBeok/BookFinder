package com.beok.domain

import com.beok.domain.entity.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    @GET("v1/volumes")
    suspend fun search(
        @Query("q")
        keyword: String,
        @Query("startIndex")
        startIndex: Int = 0,
        @Query("maxResults")
        perPage: Int = 10
    ): BooksResponse
}
