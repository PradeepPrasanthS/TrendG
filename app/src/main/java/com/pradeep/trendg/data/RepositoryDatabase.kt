package com.pradeep.trendg.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pradeep.trendg.constants.DATABASE_VERSION

@Database(entities = [RepositoryModel::class], version = DATABASE_VERSION, exportSchema = false)
abstract class RepositoryDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: RepositoryDatabase? = null

        fun getDatabase(context: Context): RepositoryDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RepositoryDatabase::class.java,
                        "repositories.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}