package com.ace.mobilecomputing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ace.mobilecomputing.NEWS_TABLE_NAME
import com.ace.mobilecomputing.data.models.NewsDataModel


@Dao
interface NewsDao : BaseDao<NewsDataModel> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun add(obj: NewsDataModel): Long

    @Query("SELECT * FROM $NEWS_TABLE_NAME")
    suspend fun getAll(): List<NewsDataModel>

    @Query("SELECT * FROM $NEWS_TABLE_NAME where localId = :id")
    suspend fun getById(id: Int): NewsDataModel?
}