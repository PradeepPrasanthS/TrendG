package com.pradeep.trendg.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Query("SELECT * from repositories")
    fun getAll(): List<RepositoryModel>

    @Insert
    suspend fun insertRepositories(repositoryModel: List<RepositoryModel>)

    @Query("DELETE from repositories")
    suspend fun deleteAll()

}
