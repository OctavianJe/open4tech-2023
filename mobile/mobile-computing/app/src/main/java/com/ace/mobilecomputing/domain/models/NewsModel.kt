package com.ace.mobilecomputing.domain.models

import com.ace.mobilecomputing.data.models.NewsDataModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date


class NewsModel(
    private val localId: Int?,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("author") val author: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("publishedAt") var publishedAt: Date
) : Serializable {
    fun toDataModel(): NewsDataModel {
        return NewsDataModel(
            localId = localId,
            id = id,
            author = author,
            title = title,
            description = description,
            publishedAt = publishedAt
        )
    }
}

fun List<NewsModel>.toDataModels(): List<NewsDataModel> {
    return this.map {
        it.toDataModel()
    }
}