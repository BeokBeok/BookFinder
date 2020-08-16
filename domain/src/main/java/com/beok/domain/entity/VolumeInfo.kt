package com.beok.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeInfo(

	@Json(name = "industryIdentifiers")
	val industryIdentifiers: List<IndustryIdentifiersItem> = emptyList(),

	@Json(name = "pageCount")
	val pageCount: Int = 0,

	@Json(name = "printType")
	val printType: String = "",

	@Json(name = "readingModes")
	val readingModes: ReadingModes = ReadingModes(),

	@Json(name = "previewLink")
	val previewLink: String = "",

	@Json(name = "canonicalVolumeLink")
	val canonicalVolumeLink: String = "",

	@Json(name = "language")
	val language: String = "",

	@Json(name = "title")
	val title: String = "",

	@Json(name = "imageLinks")
	val imageLinks: ImageLinks = ImageLinks(),

	@Json(name = "publishedDate")
	val publishedDate: String = "",

	@Json(name = "maturityRating")
	val maturityRating: String = "",

	@Json(name = "allowAnonLogging")
	val allowAnonLogging: Boolean = false,

	@Json(name = "contentVersion")
	val contentVersion: String = "",

	@Json(name = "authors")
	val authors: List<String> = emptyList(),

	@Json(name = "infoLink")
	val infoLink: String = "",

	@Json(name = "description")
	val description: String = "",

	@Json(name = "averageRating")
	val averageRating: Int = 0,

	@Json(name = "panelizationSummary")
	val panelizationSummary: PanelizationSummary = PanelizationSummary(),

	@Json(name = "publisher")
	val publisher: String = "",

	@Json(name = "ratingsCount")
	val ratingsCount: Int = 0,

	@Json(name = "categories")
	val categories: List<String> = emptyList(),

	@Json(name = "subtitle")
	val subtitle: String = ""
)
