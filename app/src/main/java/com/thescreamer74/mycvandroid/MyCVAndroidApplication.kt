package com.thescreamer74.mycvandroid

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.thescreamer74.mycvandroid.di.clientModule
import com.thescreamer74.mycvandroid.di.networkModule
import com.thescreamer74.mycvandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyCVAndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyCVAndroidApplication)
            modules(
                viewModelModule,
                clientModule,
                networkModule
            )
        }
    }
}