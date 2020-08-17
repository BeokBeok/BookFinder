package com.beok.bookfinder.datasource

import androidx.paging.PageKeyedDataSource
import com.beok.bookfinder.model.BookItem
import com.beok.bookfinder.model.mapToPresenter
import com.beok.common.ext.coroutineExceptionHandler
import com.beok.domain.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookSearchDataSource(
    private val keyword: String,
    private val booksRepository: BooksRepository
) : PageKeyedDataSource<Int, BookItem>() {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookItem>
    ) {
        ioScope.launch(coroutineExceptionHandler) {
            val result = booksRepository.searchBook(keyword = keyword, perPage = PER_PAGE)
            if (result.isSuccess) {
                val bookItems =
                    result.getOrNull()?.items?.map { it.mapToPresenter() } ?: emptyList()
                callback.onResult(bookItems, null, PER_PAGE)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookItem>) {
        ioScope.launch(coroutineExceptionHandler) {
            val result = booksRepository.searchBook(
                keyword = keyword,
                page = params.key + PER_PAGE,
                perPage = PER_PAGE
            )
            if (result.isSuccess) {
                val bookItems =
                    result.getOrNull()?.items?.map { it.mapToPresenter() } ?: emptyList()
                callback.onResult(bookItems, params.key + PER_PAGE)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookItem>) = Unit

    companion object {
        private const val PER_PAGE = 40
    }
}
