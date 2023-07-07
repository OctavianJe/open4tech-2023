package com.ace.mobilecomputing.services.shared_preferences

import android.app.Application


class SharedPreferencesServiceLazyLoading(application: Application) {
    val sharedPreferencesService: SharedPreferencesService by lazy {
        SharedPreferencesService(application)
    }
}