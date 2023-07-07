package com.ace.mobilecomputing.domain.services.api

import com.ace.mobilecomputing.API_REQUEST_LOGIN_TIMEOUT
import com.ace.mobilecomputing.API_REQUEST_TIMEOUT
import com.ace.mobilecomputing.domain.miscellaneous.DateType
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseAPIService {
    companion object {
        inline fun <reified T : Any> createService(
            baseUrl: String,
            interceptors: List<Interceptor> = listOf(),
            loggingLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC,
            customTimeout: Boolean = false,
            customLoginTimeout: Boolean = false
        ): T {
            val gson = GsonBuilder().setDateFormat(DateType.FULL.value).create()

            val logger = HttpLoggingInterceptor().apply { level = loggingLevel }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)

            if (customTimeout) {
                val requestTimeout: Long = getRequestTimeout(customLoginTimeout)

                client.apply {
                    callTimeout(requestTimeout, TimeUnit.SECONDS)
                    readTimeout(requestTimeout, TimeUnit.SECONDS)
                    connectTimeout(requestTimeout, TimeUnit.SECONDS)
                    writeTimeout(requestTimeout, TimeUnit.SECONDS)
                }
            }

            client.apply { interceptors.forEach { addInterceptor(it) } }

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(T::class.java)
        }

        fun getRequestTimeout(customLoginTimeout: Boolean): Long {
            return if (customLoginTimeout)
                API_REQUEST_LOGIN_TIMEOUT
            else
                API_REQUEST_TIMEOUT
        }
    }
}