package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PanelizationSummary(

	@Json(name = "containsImageBubbles")
	val containsImageBubbles: Boolean = false,

	@Json(name = "containsEpubBubbles")
	val containsEpubBubbles: Boolean = false
)
