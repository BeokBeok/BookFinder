package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IndustryIdentifiersItem(

    @Json(name = "identifier")
    val identifier: String = "",

    @Json(name = "type")
    val type: String = ""
)
