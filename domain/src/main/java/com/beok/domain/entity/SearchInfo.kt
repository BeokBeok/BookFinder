package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchInfo(

	@Json(name = "textSnippet")
	val textSnippet: String = ""
)
