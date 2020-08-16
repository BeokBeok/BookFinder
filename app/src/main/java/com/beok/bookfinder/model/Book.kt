package com.beok.bookfinder.model

import com.beok.domain.entity.BooksResponse

data class Book(
    val totalItems: Int,
    val items: List<BookItem>
)

fun BooksResponse.mapToPresenter() = Book(
    totalItems = totalItems,
    items = items.map { it.mapToPresenter() }
)
