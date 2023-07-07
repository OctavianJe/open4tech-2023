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


class SummaryDataSource @Inject constructor(
    private val dao: NewsDao,
    private val service: SummariseService
) : BaseDataSource<SummariseModel, NewsDataModel>(dao) {

    suspend fun getSummarisedNews(text: String): ApiRequestResponse<SummariseModel> {
        return call(service::summarise, "Error getting the summary")
    }
}