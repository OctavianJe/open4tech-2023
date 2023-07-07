package com.ace.mobilecomputing.domain.services

import com.ace.mobilecomputing.domain.models.NewsModel
import com.ace.mobilecomputing.domain.models.SummariseModel
import retrofit2.http.GET
import retrofit2.http.POST


interface SummariseService {
    @POST("/summarize")
    suspend fun summarise(): SummariseModel
}