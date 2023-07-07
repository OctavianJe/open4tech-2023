package com.ace.mobilecomputing.domain.services

import com.ace.mobilecomputing.domain.models.NewsModel
import retrofit2.http.GET


interface NewsService {
    @GET("News/GetAllNews")
    suspend fun getAllNews(): List<NewsModel>

    @GET("News/GetNewsById")
    suspend fun getNewsById(): NewsModel
}