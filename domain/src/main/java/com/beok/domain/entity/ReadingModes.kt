package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReadingModes(

	@Json(name = "image")
	val image: Boolean = false,

	@Json(name = "text")
	val text: Boolean = false
)
