package com.ace.mobilecomputing.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(objs: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(objs: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(objs: List<T>)

    @Delete
    suspend fun delete(obj: T)

    @Delete
    suspend fun delete(objs: List<T>)
}