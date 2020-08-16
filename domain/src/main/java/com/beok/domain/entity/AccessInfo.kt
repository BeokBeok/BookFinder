package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessInfo(

	@Json(name = "accessViewStatus")
	val accessViewStatus: String = "",

	@Json(name = "country")
	val country: String = "",

	@Json(name = "viewability")
	val viewability: String = "",

	@Json(name = "pdf")
	val pdf: Pdf = Pdf(),

	@Json(name = "webReaderLink")
	val webReaderLink: String = "",

	@Json(name = "epub")
	val epub: Epub = Epub(),

	@Json(name = "publicDomain")
	val publicDomain: Boolean = false,

	@Json(name = "quoteSharingAllowed")
	val quoteSharingAllowed: Boolean = false,

	@Json(name = "embeddable")
	val embeddable: Boolean = false,

	@Json(name = "textToSpeechPermission")
	val textToSpeechPermission: String = ""
)
