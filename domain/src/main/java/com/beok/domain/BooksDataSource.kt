package com.beok.domain

import com.beok.domain.entity.BooksResponse

interface BooksDataSource {

    suspend fun searchBook(bookName: String): Result<BooksResponse>
}
