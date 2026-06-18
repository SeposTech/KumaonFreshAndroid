package com.himanshumehra.kumaonfresh.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KumaonFreshApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}