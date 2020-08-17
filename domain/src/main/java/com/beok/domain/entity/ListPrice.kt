package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListPrice(

	@Json(name = "amount")
	val amount: Int? = null,

	@Json(name = "currencyCode")
	val currencyCode: String? = null,

	@Json(name = "amountInMicros")
	val amountInMicros: Long? = null
)
