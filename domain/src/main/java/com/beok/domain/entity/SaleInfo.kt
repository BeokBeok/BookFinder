package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SaleInfo(

	@Json(name = "country")
	val country: String = "",

	@Json(name = "isEbook")
	val isEbook: Boolean = false,

	@Json(name = "saleability")
	val saleability: String = ""
)
