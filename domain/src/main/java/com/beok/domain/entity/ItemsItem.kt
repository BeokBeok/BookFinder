package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsItem(

	@Json(name = "saleInfo")
	val saleInfo: SaleInfo = SaleInfo(),

	@Json(name = "kind")
	val kind: String = "",

	@Json(name = "volumeInfo")
	val volumeInfo: VolumeInfo = VolumeInfo(),

	@Json(name = "etag")
	val etag: String = "",

	@Json(name = "id")
	val id: String = "",

	@Json(name = "accessInfo")
	val accessInfo: AccessInfo = AccessInfo(),

	@Json(name = "selfLink")
	val selfLink: String = "",

	@Json(name = "searchInfo")
	val searchInfo: SearchInfo = SearchInfo()
)
