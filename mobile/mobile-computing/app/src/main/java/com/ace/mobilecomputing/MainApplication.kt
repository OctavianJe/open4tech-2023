package com.ace.mobilecomputing

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesServiceLazyLoading
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    lateinit var sharedPreferencesServiceLazyLoading: SharedPreferencesServiceLazyLoading

    private var defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        appInitialization();

        instance = this
        sharedPreferencesServiceLazyLoading = SharedPreferencesServiceLazyLoading(this)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.VERBOSE)
            .setWorkerFactory(workerFactory)
            .build()

    private fun appInitialization() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(unCaughtExceptionHandler);
    }

    private val unCaughtExceptionHandler =
        Thread.UncaughtExceptionHandler { _ , ex ->
            ex.printStackTrace()
        }
}