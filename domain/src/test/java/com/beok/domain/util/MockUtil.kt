package com.beok.domain.util

import com.beok.domain.entity.BooksResponse

object MockUtil {

    fun mockToBooksResponse() = BooksResponse(
        totalItems = 229,
        kind = "books#volumes",
        items = listOf()
    )
}
