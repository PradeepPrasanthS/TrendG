package com.pradeep.trendg.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryModel(
    @PrimaryKey(autoGenerate = true)
    val repositoryId: Int,
    val author: String,
    var avatar: String,
    val name: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: String
)
