package com.beok.domain

import com.beok.domain.entity.BooksResponse

interface BooksDataSource {

    suspend fun searchBook(bookName: String, page: Int = 0): Result<BooksResponse>
}
