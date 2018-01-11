package com.sample.perform.app.data.model

data class ArticlesItem(
	val publishedTime: Long,
	val language: String,
	val id: String,
	val headline: String,
	val teaser: String,
	val lastUpdateTime: Long
)
