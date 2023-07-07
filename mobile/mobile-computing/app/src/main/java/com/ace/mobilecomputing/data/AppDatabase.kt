package com.ace.mobilecomputing.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ace.mobilecomputing.DATABASE_NAME
import com.ace.mobilecomputing.data.dao.*
import com.ace.mobilecomputing.data.helpers.Converters
import com.ace.mobilecomputing.data.models.*


@Database(
    entities = [
        NewsDataModel::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {})
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
