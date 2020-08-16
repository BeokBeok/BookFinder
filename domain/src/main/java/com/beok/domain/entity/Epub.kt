package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Epub(

	@Json(name = "isAvailable")
	val isAvailable: Boolean = false
)
