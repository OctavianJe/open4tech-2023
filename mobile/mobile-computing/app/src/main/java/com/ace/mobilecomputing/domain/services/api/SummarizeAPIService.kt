package com.ace.mobilecomputing.domain.services.api

import com.ace.mobilecomputing.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor


class SummarizeAPIService : BaseAPIService() {
    companion object {
        inline fun <reified T: Any> create(
            interceptors: List<Interceptor> = listOf(),
            loggingLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC,
            customTimeout: Boolean = false,
            customLoginTimeout: Boolean = false,
        ): T = createService(
            baseUrl = BuildConfig.ML_BASE_URL,
            interceptors = interceptors,
            loggingLevel = loggingLevel,
            customTimeout = customTimeout,
            customLoginTimeout = customLoginTimeout
        )
    }
}