package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(

	@Json(name = "totalItems")
	val totalItems: Int = 0,

	@Json(name = "kind")
	val kind: String = "",

	@Json(name = "items")
	val items: List<ItemsItem> = emptyList()
)
