package com.ace.mobilecomputing.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ace.mobilecomputing.NEWS_TABLE_NAME
import com.ace.mobilecomputing.domain.models.NewsModel
import java.util.Date


@Entity(
    tableName = NEWS_TABLE_NAME,
    indices = [Index(value = ["id"], unique = true)]
)
data class NewsDataModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localId") val localId: Int?,
    val id: String,
    val author: String,
    var title: String,
    var description: String,
    var publishedAt: Date
)

fun NewsDataModel.toDomainModel(): NewsModel {
    return NewsModel(
        localId,
        id,
        author,
        title,
        description,
        publishedAt
    )
}

fun List<NewsDataModel>.toDomainModels(): List<NewsModel> {
    return this.map {
        it.toDomainModel()
    }
}