package com.ace.mobilecomputing.data.repositories

import com.ace.mobilecomputing.data.data_sources.NewsDataSource
import com.ace.mobilecomputing.data.data_sources.SummaryDataSource
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.domain.models.NewsModel
import com.ace.mobilecomputing.domain.models.SummariseModel
import com.ace.mobilecomputing.domain.network.ApiRequestResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(
    private val source: NewsDataSource,
    private val summaryDataSource: SummaryDataSource
) : BaseRepository<NewsModel, NewsDataModel>(source) {

    suspend fun getAllNews(): ApiRequestResponse<List<NewsModel>> {
        return source.getAllNews()
    }

    suspend fun getNewsById(): ApiRequestResponse<NewsModel> {
        return source.getNewsById()
    }

    suspend fun getSummary(text: String): ApiRequestResponse<SummariseModel> {
        return summaryDataSource.getSummarisedNews(text)
    }

    suspend fun getAll(): List<NewsDataModel> {
        return source.getAll()
    }

    suspend fun getById(id: Int): NewsDataModel? {
        return source.getById(id)
    }
}