package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageLinks(

	@Json(name = "thumbnail")
	val thumbnail: String = "",

	@Json(name = "smallThumbnail")
	val smallThumbnail: String = ""
)
