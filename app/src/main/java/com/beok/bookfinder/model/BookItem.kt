package com.beok.bookfinder.model

import com.beok.domain.entity.ItemsItem

data class BookItem(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val imageUrl: String
)

fun ItemsItem.mapToPresenter() = BookItem(
    title = volumeInfo.title,
    authors = volumeInfo.authors,
    publishedDate = volumeInfo.publishedDate,
    imageUrl = volumeInfo.imageLinks.thumbnail
)
