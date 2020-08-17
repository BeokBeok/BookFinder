package com.beok.bookfinder.datasource

import androidx.paging.DataSource
import com.beok.bookfinder.model.BookItem
import com.beok.common.event.ViewModelToViewEvent
import com.beok.domain.BooksRepository

class BookSearchDataSourceFactory(
    private val keyword: String,
    private val booksRepository: BooksRepository,
    private val viewModelToViewEvent: ViewModelToViewEvent
) : DataSource.Factory<Int, BookItem>() {

    override fun create(): DataSource<Int, BookItem> =
        BookSearchDataSource(
            keyword = keyword,
            booksRepository = booksRepository,
            viewModelToViewEvent = viewModelToViewEvent
        )
}
