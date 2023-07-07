package com.ace.mobilecomputing.presentation.viewmodels

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.viewModelScope
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.data.repositories.NewsRepository
import com.ace.mobilecomputing.domain.models.toDataModels
import com.ace.mobilecomputing.domain.network.ApiRequestResponse
import com.ace.mobilecomputing.services.Utilities
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val utilities: Utilities,
    private val sharedPreferencesService: SharedPreferencesService
) : BaseViewModel() {

    fun checkWiFiModule(activity: Activity, then: (wiFi: Boolean) -> Unit) {
        val connMgr = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isWifiConn: Boolean = false
        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
            }
        }
        then(isWifiConn)
    }

    fun refreshContent() {
        viewModelScope.launch {
            val response = newsRepository.getAllNews()
            //If we get a success response, we will add into our local database the products
            if (response is ApiRequestResponse.Success) {
                newsRepository.addLocal(response.data.toDataModels())
            } else {
                // TODO: Display toast with error message
            }
        }
    }

    fun initRecycleView(): List<NewsDataModel> {
        var products: List<NewsDataModel> = emptyList()
        runBlocking {
            launch(Dispatchers.IO) {
                products = newsRepository.getAll()
            }.join()
        }
        return products
    }
}