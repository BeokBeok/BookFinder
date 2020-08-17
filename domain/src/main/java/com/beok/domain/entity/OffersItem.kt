package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OffersItem(

	@Json(name = "finskyOfferType")
	val finskyOfferType: Int = 0,

	@Json(name = "retailPrice")
	val retailPrice: RetailPrice = RetailPrice(),

	@Json(name = "listPrice")
	val listPrice: ListPrice = ListPrice()
)
