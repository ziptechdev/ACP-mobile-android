package com.acpmobile.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    internal fun provideResources(appContext: Context): Resources {
        return appContext.resources
    }

    @Provides
    @Singleton
    internal fun provideNavigator(): Navigation {
        return Navigation()
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(appContext: Context): SharedPreferences {
        return androidx.preference.PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferencesHelper(appContext: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(provideSharedPreferences(appContext))
    }
}