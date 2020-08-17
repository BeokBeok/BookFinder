package com.beok.domain

import com.beok.domain.entity.BooksResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepository @Inject constructor(private val booksService: BooksService) :
    BooksDataSource {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun searchBook(bookName: String, page: Int): Result<BooksResponse> =
        withContext(ioDispatcher) {
            runCatching { booksService.search(bookName, page) }
        }
}
