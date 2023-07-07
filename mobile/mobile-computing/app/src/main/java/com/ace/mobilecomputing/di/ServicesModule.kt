package com.ace.mobilecomputing.di

import android.app.Application
import android.content.Context
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import com.ace.mobilecomputing.services.Utilities
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ServicesModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideSharedPreferencesService(@ApplicationContext context: Context): SharedPreferencesService {
        return SharedPreferencesService(context)
    }

    @Singleton
    @Provides
    fun provideUtilities(
        @ApplicationContext context: Context,
        sharedPreferencesService: SharedPreferencesService
    ): Utilities {
        return Utilities(context, sharedPreferencesService)
    }

    @Singleton
    @Provides
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Singleton
    @Provides
    fun provideActivityRecognitionClient(@ApplicationContext context: Context): ActivityRecognitionClient {
        return ActivityRecognition.getClient(context)
    }

    @Singleton
    @Provides
    fun providePicasso(): Picasso {
        return Picasso.get()
    }
}