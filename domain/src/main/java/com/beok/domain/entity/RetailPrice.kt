package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RetailPrice(

	@Json(name = "amount")
	val amount: Int = 0,

	@Json(name = "currencyCode")
	val currencyCode: String = "",

	@Json(name = "amountInMicros")
	val amountInMicros: Long = 0L
)
