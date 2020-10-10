package com.pradeep.trendg.api

data class RepositoryModel(
    val author: String,
    var avatar: String,
    val name: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: String
)
