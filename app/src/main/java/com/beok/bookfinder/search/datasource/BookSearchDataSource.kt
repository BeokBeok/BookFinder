package com.beok.bookfinder.search.datasource

import androidx.paging.PageKeyedDataSource
import com.beok.bookfinder.model.BookItem
import com.beok.bookfinder.model.mapToPresenter
import com.beok.common.event.ViewModelToViewEvent
import com.beok.domain.BooksRepository
import com.beok.domain.entity.ItemsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookSearchDataSource(
    private val keyword: String,
    private val booksRepository: BooksRepository,
    private val viewModelToViewEvent: ViewModelToViewEvent
) : PageKeyedDataSource<Int, BookItem>() {

    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelToViewEvent.showErrorMessage(throwable.message ?: "")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookItem>
    ) {
        ioScope.launch(coroutineExceptionHandler) {
            viewModelToViewEvent.showLoading(true)
            val result = booksRepository.searchBook(keyword = keyword, perPage = PER_PAGE)
            if (result.isFailure) {
                viewModelToViewEvent.showErrorMessage(result.exceptionOrNull()?.message ?: "")
                viewModelToViewEvent.showLoading(false)
                return@launch
            }
            val bookItems =
                result.getOrNull()?.items?.map(ItemsItem::mapToPresenter) ?: emptyList()
            callback.onResult(bookItems, null, PER_PAGE)
            viewModelToViewEvent.showLoading(false)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookItem>) {
        ioScope.launch(coroutineExceptionHandler) {
            viewModelToViewEvent.showLoading(true)
            val result = booksRepository.searchBook(
                keyword = keyword,
                page = params.key + PER_PAGE,
                perPage = PER_PAGE
            )
            if (result.isFailure) {
                viewModelToViewEvent.showErrorMessage(result.exceptionOrNull()?.message ?: "")
                viewModelToViewEvent.showLoading(false)
                return@launch
            }
            val bookItems =
                result.getOrNull()?.items?.map(ItemsItem::mapToPresenter) ?: emptyList()
            callback.onResult(bookItems, params.key + PER_PAGE)
            viewModelToViewEvent.showLoading(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookItem>) = Unit

    companion object {
        private const val PER_PAGE = 40
    }
}
