package com.ace.mobilecomputing.data.data_sources

import com.ace.mobilecomputing.data.dao.NewsDao
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.domain.models.NewsModel
import com.ace.mobilecomputing.domain.models.SummariseModel
import com.ace.mobilecomputing.domain.network.ApiRequestResponse
import com.ace.mobilecomputing.domain.services.NewsService
import com.ace.mobilecomputing.domain.services.SummariseService
import com.ace.mobilecomputing.domain.services.api.SummarizeAPIService
import javax.inject.Inject


class NewsDataSource @Inject constructor(
    private val dao: NewsDao,
    private val service: NewsService,
    private val mlService: SummariseService
) : BaseDataSource<NewsModel, NewsDataModel>(dao) {
    suspend fun getAllNews(): ApiRequestResponse<List<NewsModel>> {
        return call(service::getAllNews, "Error getting all the news")
    }

    suspend fun getNewsById(): ApiRequestResponse<NewsModel> {
        return call(service::getNewsById, "Error getting the news")
    }

    suspend fun getSummarisedNews(text: String): ApiRequestResponse<SummariseModel> {
        return call(mlService::summarise, "Error getting the summary")
    }

    suspend fun getAll(): List<NewsDataModel> {
        return dao.getAll()
    }

    suspend fun getById(domainId: Int): NewsDataModel? {
        return dao.getById(domainId)
    }
}