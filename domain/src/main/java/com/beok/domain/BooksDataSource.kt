package com.beok.domain

import com.beok.domain.entity.BooksResponse

interface BooksDataSource {

    suspend fun searchBook(keyword: String, page: Int = 0, perPage: Int = 10): Result<BooksResponse>
}
