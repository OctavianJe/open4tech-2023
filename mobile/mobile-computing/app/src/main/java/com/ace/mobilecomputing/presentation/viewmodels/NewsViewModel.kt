package com.ace.mobilecomputing.presentation.viewmodels


import androidx.lifecycle.viewModelScope
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.data.repositories.NewsRepository
import com.ace.mobilecomputing.domain.models.SummariseModel
import com.ace.mobilecomputing.domain.models.toDataModels
import com.ace.mobilecomputing.domain.network.ApiRequestResponse
import com.ace.mobilecomputing.services.Utilities
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val utilities: Utilities,
    private val sharedPreferencesService: SharedPreferencesService
) : BaseViewModel() {

    suspend fun getNews(localId: Int): NewsDataModel? {
        return newsRepository.getById(localId)
    }

    fun getSummary(text: String): String {
        var return_text = ""
        runBlocking {
            val response = newsRepository.getSummary(text)
            //If we get a success response, we will add into our local database the products
            if (response is ApiRequestResponse.Success) {
                return_text = response.data.summary
            } else {
                return_text = ""
            }
        }
        return return_text
    }
}