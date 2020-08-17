package com.beok.bookfinder.model

import com.beok.domain.entity.ItemsItem

data class BookItem(
    val id: String,
    val title: String,
    val authors: String,
    val publishedDate: String,
    val imageUrl: String
)

fun ItemsItem.mapToPresenter() = BookItem(
    id = id,
    title = volumeInfo.title,
    authors = volumeInfo.authors.joinToString(),
    publishedDate = volumeInfo.publishedDate,
    imageUrl = volumeInfo.imageLinks.thumbnail
)
