package com.kumail.cakes

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by kumailhussain on 16/11/2021.
 */
@HiltAndroidApp
class CakesApp : Application() {

    companion object {
        lateinit var instance: CakesApp private set
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupTimber()
        instance = this
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}